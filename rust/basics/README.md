# Rust Baiscs
안정성, 손쉬운 동시성 추구 

<br>

# Variable
## 1) mutability
기본 변수는 '불변성'이다. 가변성을 주기 위해서는 'mut'을 사용해야 한다
```rust 
let mut x = 5;
```

또는 let 키워드를 반복해서 shadowing을 할 수 있다. 
```rust 
let y = 5; // immutable
// y = y+1; // error: cannot assign twice to immutable variable `y`
let y = y+1; // shadowing
```

그리고 상수(const)에 대해서는 'mut'이 허용되지 않으며 타입을 꼭 명시해줘야 한다. 
```rust
const MAX_POINTS: u32 = 100_000;
```

<br>

## 2) ownership
러스트의 핵심 기능은 바로 소유권이다. 
1. 러스트의 각각의 값은 해당값의 오너(owner)라고 불리우는 변수를 갖고 있다.
2. 한번에 딱 하나의 오너만 존재할 수 있다.
3. 오너가 스코프 밖으로 벗어나는 때, 값은 버려진다(dropped).
``` rust
fn ownership(){
    let mut vec = Vec::new();
    vec.push(1);
    vec.push(2);
    take(vec); // 소유권 넘김
    // vec.push(3); // error: borrow of moved value: `vec`

    let x = 5;
    copy(x); // 복사 
    println!("out x = {}", x); // 5
}

fn take(vec: Vec<i32>){ // vec 소유권 받음 
    println!("vec[0] = {}", vec[0]);
} // drop 호출. 메모리 해제 

fn copy(x: i32){ // x 복사
    println!("in x = {}", x);
} // 아무 일도 발생하지 않음 
```

<br>

### stack 구조 활용 
러스트는 런타임 시점에 동적으로 관리되는 메모리(객체)를 '{', '}' in & out을 통해 추적하고 관리한다. 
```rust
{
    let s = String::from("hello"); // s는 여기서부터 유효

    // s를 가지고 do something
}                                  // 이 스코프는 끝났고, s는 더 이상 
                                   // 유효하지 않다
```

<br>

### 객체 얕은 복사
러스트는 얕은 복사를 하면 복사가 된 객체는 deprecated 시킨다. 나중에 2개를 동시에 메모리 해제를 하는 double free 오류를 방지하기 위해서이다. 
```rust
let s1 = String::from("hello");
let s2 = s1;

// println!("{}, world!", s1); // error[E0382]: borrow of moved value: `s1
```

컴파일 타임에 결정되는 타입 변수는 스택에 저장되기 때문에 위와 같은 경우가 발생하지 않는다. 힙에 저장되는 객체들만 위의 동작이 유효하다. 
```rust
let q = 5;
let w = q;

println!("q = {}, w = {}", q, w);
```

<br>

## 3) borrowing
소유권을 넘기는 대신 참조(reference)를 통해 빌려주는 방식도 있다. 
- &: 참조자 
```rust
fn main() {
    let s1 = String::from("hello");
    let len = calculate_length(&s1);

    println!("The length of '{}' is {}.", s1, len);
}

fn calculate_length(s: &String) -> usize {   // s는 s1을 참조한다
    s.len()
} // 갖고있는 소유권이 없기 때문에 아무일도 발생하지 않음 
```
![Alt text|850|750](image.png)

<br>

### 가변 참조 
여기서도 러스트 특징이 있는데 가변 참조자는 딱 하나만 만들 수 있다. 
```rust
 // mut를 사용하면 빌려서 값을 변경할 수 있다
let mut s = String::from("hello");
change(&mut s);
println!("s = {}", s);

// 가변 참조자는 오직 하나만 존재할 수 있다
let r1 = &mut s;
println!("r1 = {}", r1); 
let r2 = &mut s; 
println!("r2 = {}", r2); 

// 동시 접근하면 문제 발생 
// println!("r1, r2= {}, {}", r1, r2); // error[E0499]: cannot borrow `s` as mutable more than once at a time
```

이는 race condition 동시성 문제를 방지하기 위해서이다. 
- 두 개 이상의 포인터가 동시에 같은 데이터에 접근한다.
- 그 중 적어도 하나의 포인터가 데이터를 쓴다.
- 데이터에 접근하는데 동기화를 하는 어떠한 메커니즘도 없다.

또한 불변 참조자를 가지고 있을 경우에도 가변 참조자를 만들 수 없다 
```rust 
let mut ss = String::from("hello");
let rr1 = &ss; // 문제 없음
let rr2 = &ss; // 문제 없음
let rr3 = &mut ss; // 큰 문제
// println!("{}, {}, and {}", rr1, rr2, rr3); // error[E0502]: cannot borrow `ss` as mutable because it is also borrowed as immutable
```

<br>

### dangling pointer
어떤 메모리를 가리키는 포인터가 남아있는 상황에서 일부 메모리를 해제해 버림으로써, 다른 개체가 할당받았을지도 모르는 메모리를 참조하게 된 포인터를 말한다. 

러스트는 데이터 참조자가 생성되면 스코프({,})를 벗어나기 전에 데이터가 먼저 벗어나는지 컴파일러에서 체크한다. 
```rust 
fn dangle() -> &String{ // String의 참조자를 반환
    let s = String::from("hello");
    &s // s의 참조자를 반환
} // s는 스코프 밖으로 벗어나고 drop 호출. 메모리 해제. 위험하다 

fn no_dangle() -> String{ // String을 반환
    let s = String::from("hello");
    s // s의 소유권을 반환
} // s는 스코프 밖으로 벗어나고 drop 호출. 메모리 해제. 안전하다
```

<br>

### 배열 slice
배열 또한 객체이므로 힙에서 관리된다. 배열을 slice하는 경우에는 연속된 일련의 요소를 참조하게 해준다. 소유권 이전이 아니다. 
```rust
let s = String::from("hello world");
let hello = &s[0..5];
let world = &s[6..11];
println!("hello = {}, world = {}", hello, world);
```
<img width="350" alt="스크린샷 2023-09-05 오후 9 06 23" src="https://github.com/loosie/code_playground/assets/54282927/2b2fcaaf-fa08-487a-99f2-20d284439a32">


<br>

```rust 
fn slice(){
    let mut ss = String::from("hello world");
    let word = first_word(&ss); // word 불변 참조자

    // clear()함수는 ss를 변경해야 하므로 가변 참조자가 필요하다. 그래서 컴파일 에러 발생 
    // ss.clear(); // error[E0502]: cannot borrow `ss` as mutable because it is also borrowed as immutable
    println!("the first word is: {}", word);
}

fn first_word(s: &String) -> &str {
    let bytes = s.as_bytes();

    for (i, &item) in bytes.iter().enumerate() {
        if item == b' ' {
            return &s[0..i];
        }
    }

    &s[..]
}
```

<br>

# Struct 
다양한 형태로 구조체 활용 가능 
```rust 
struct User {
    active: bool,
    username: String,
    email: String,
    sign_in_count: u64,
}

fn build_user(email: String, username: String) -> User {
    User {
        active: true,
        username, // 중복 명시 없이 이렇게 해도 값 대입이 됨
        email,    //              '' 
        sign_in_count: 1,
    }
}

struct Point(i32, i32, i32);
```

## 구조체 소유권? 
User 구조체 정의에서는 의도적으로 &str 문자열 슬라이스 대신 구조체가 소유권을 갖는 String 타입을 사용했다. 구조체 인스턴스가 유효한 동안 각 인스턴스 내의 모든 데이터가 유효하도록 만들기 위해서다. 소유권을 갖기 않기 위해서 참조자를 사용할 수 있지만 lifetime 활용해야 한다. 

```rust 
struct User {
    active: bool,
    username: &str, // expected named lifetime parameter
    email: &str,   // expected named lifetime parameter
    sign_in_count: u64,
}
```

<br>

# 열거형 
러스트는 Null이 없다. 다음과 같이 Option 열거형을 사용한다. 
```rust 
enum Option<T> {
    None,
    Some(T),
}
```

if, else는 다른 프로그래밍과 같다. 그런데 러스트는 특유의 match라고 부르는 강력한 제어 흐름 연산자를 갖고 있다. 패턴은 리터럴 값, 변수명, 와일드카드 등 다양한 것으로 구성 가능하다. 
```rust
fn give_vegan(drink: Option<&str>){
    // if drink == Some("milk") { panic!("Hey, this is milk!"); }
    // if drink == None { println!("What? no drink?"); }
    // else { println!("I love {}!!", drink.unwrap()); }

    match drink {
        Some("milk") => panic!("Hey, this is milk!"),
        None => println!("What? no drink?"),
        Some(inner) => println!("I love {}!!", inner),
    }
}
```
- Some(inner)는 _, other로도 대체가 가능하다. 대신 unwrap은 필요. 

<br>

# Collection
## vector
```rust
use std::collections::HashMap;

fn vector(){
    let v: Vec<i32> = Vec::new();
    let v2 = vec![1, 2, 3];
    // v2.push(2); //  cannot borrow `v2` as mutable, as it is not declared as mutable
    println!("v2[2] = {}", &v2[2]); // v2[2] = 3
    for i in &v2 {
        println!("{}", i);
    }
    for i in 0..=3 {
        let opget = v2.get(i);
        match opget {
            Some(inner) => println!("v2[{}] = {}", i, inner),
            None => println!("v2[{}] = None", i),
        }
    }

    let mut v = Vec::new();
    v.push(5);
    v.push(6);
    v.push(7);
    v.push(8);
    println!("v = {:?}", v); // v = [5, 6, 7, 8]

    for i in &mut v {
        *i += 50;
    }
    println!("v = {:?}", v); // v = [55, 56, 57, 58]
}
```

## hashMap
```rust

fn hashMap() {
    let mut scores = HashMap::new();
    scores.insert(String::from("Blue"), 10);

    scores.entry(String::from("Yellow")).or_insert(50);
    scores.entry(String::from("Blue")).or_insert(50);

    println!("{:?}", scores); // {"Yellow": 50, "Blue": 10}

    let field_name = String::from("Favorite color");
    let field_value = String::from("Blue");

    let mut map = HashMap::new();
    map.insert(field_name, field_value); // field_name, field_value 소유권 이전
    // println!("{}, {}", field_name, field_value); // error[E0382]: borrow of moved value: `field_name` (value moved into `map`
    println!("{:?}", map); // {"Favorite color": "Blue"}
}
```

<br>

# generic
제네릭을 사용하면 함수 시그니처나 구조체의 아이템에 다양한 구체적 데이터 타입을 사용할 수 있도록 정의할 수 있다. 

## 제네릭 사용하기 전
```rust 
fn largest_i32(list: &[i32]) -> &i32 {
    let mut largest = &list[0];

    for item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}

fn largest_char(list: &[char]) -> &char {
    let mut largest = &list[0];

    for item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}
```

## 제네릭 사용
```rust 
fn largest<T>(list: &[T]) -> &T {
    let mut largest = &list[0];

    for item in list {
        if item > largest {
            largest = item;
        }
    }

    largest
}
```

## 제네릭 메서드 
Point<T> 구조체에 x 메서드를 구현한 모습이다. 
```rust 
struct Point<T> {
    x: T,
    y: T,
}

impl<T> Point<T> {
    fn x(&self) -> &T {
        &self.x
    }
}

```

## 제네릭 성능
제네릭 타입의 사용이 구체적인 타입을 사용했을 때와 비교해서 전혀 느려지지 않다고 한다. 
러스트 컴파일러는 제네릭을 사용하는 코드를 단형상화(monomorphization)한다. 즉 컴파일러는 제네릭 코드가 호출된 곳을 전부 찾고, 제네릭 코드가 호출할 때 사용된 구체 타입으로 코드를 컴파일 시점에 생성한다.
- 단형상화: 제네릭 코드를 실제 구체 타입으로 채워진 특정 코드로 바꾸는 과정 


<br>

# 다형성과 트레잇
러스트는 트레잇으로 다형성을 지원한다. 트레잇은 메소드의 집합을 구조체(strcut) 데이터 타입에 연결할 수 있게 해준다. 

```rust
struct SeaCreature {
    pub name: String,
    noise: String,
}

impl SeaCreature {
    pub fn get_sound(&self) -> &str {
        &self.noise
    }
}

trait NoiseMaker {
    fn make_noise(&self);
}

// 트레잇 구현
impl NoiseMaker for SeaCreature {
    fn make_noise(&self) {
        println!("{}", &self.get_sound());
    }
}
```

## 트레잇 상속
트레잇은 다른 트레잇의 메소드를 상속받을 수 있다 
```rust
// 트레잇 상속
trait LoudNoiseMaker: NoiseMaker {
    fn make_alot_of_noise(&self) {
        println!("making a lot of noise");
        self.make_noise();
        self.make_noise();
        self.make_noise();
    }
}

impl LoudNoiseMaker for SeaCreature {}

fn main() {
    let creature = SeaCreature {
        name: String::from("Ferris"),
        noise: String::from("blub"),
    };

    creature.make_alot_of_noise();
}
```

## 동적 vs 정적 디스패치
- 정적 디스패치: 인스턴스 데이터 타입을 알고 있는 경우, 어떤 함수를 호출해야 하는지 정확히 알고 있다. 
- 동적 디스패치: 인스턴스 데이터 타입을 모르는 경우, 올바른 함수를 호출할 방법을 찾아야 한다.

동적 디스패치인 경우 트레잇 자료형인 `&dyn MyTrait`을 통해 객체 인스턴스들을 간접적으로 작동시킬 수 있게 한다.Rust에서는 동적 디스패치를 사용할 경우 사람들이 알 수 잇도록 트레잇 자료형 앞에 dyn 붙일 것을 권고한다.
```rust
fn static_make_noise(creature: &SeaCreature) {
    creature.make_noise();
}

fn dynamic_make_noise(creature: &dyn NoiseMaker) {
    creature.make_noise();
}

fn main() {
    let creature = SeaCreature {
        name: String::from("Ferris"),
        noise: String::from("blub"),
    };

    static_make_noise(&creature);
    dynamic_make_noise(&creature);
}
```

## 트레잇과 제네릭
둘은 함께 작동한다. 매개변수 데이터 타입 T를 정의할 때 해당 인자가 어떤 트레잇을 구현해야 하는지 나열함으로써 인자에 어떤 데이터 티입을 쓸 수 있는지 제한할 수 있다.
```rust
// fn generic_make_noise(creature: &T)
// where
//     T: NoiseMaker,
// or
// fn generic_make_noise<T: NoiseMaker>(creature: &T)
// or
fn generic_make_noise(creature: &impl NosieMaker)
{
    creature.make_noise();
}
```

<br>

# Smart Pointer
## Box
스택에 있는 데이터를 힙으로 옮길 수 있게 해주는 자료구조다. 
- 스마트 포인터. 힙에 있는 데이터를 가리키는 포인터를 들고 있음
- 필드의 크기를 알아야 하는 구조체에 뭔가의 참조를 저장할 때 종종 사용
```rust 
struct Ocean {
    animals: Vec<Box<dyn NoiseMaker>>,
}

fn main() {
    let creature = SeaCreature {
        name: String::from("Ferris"),
        noise: String::from("blub"),
    };

    let sarah = SeaCreature {
        name: String::from("Sarah"),
        noise: String::from("swish"),
    };

    let ocean = Ocean {
        animals: vec![Box::new(ferris), Box::new(sarah)],
    };

    for a in ocean.animals.iter() {
        a.make_noise();
    }
```

## Rc
스택에 있는 데이터를 힙으로 옮겨주는 스마트 포인터.
- Box와 기능은 같지만 추가적으로 immutable borrowing 기능을 갖는 다른 Rc 복제 가능(clone())하다. 
```rust
use std::rc::Rc;

struct SPie;
impl SPie {
    fn eat(&self) {
        println!("Pie eaten!");
    }
}

fn main() {
    // 2. Rc
    let heap_pie = Box::new(SPie);
    heap_pie.eat();

    let heap_pie2 = Rc::new(SPie);
    heap_pie2.eat();

    let clone_pie = heap_pie2.clone(); // immutable borrow
    clone_pie.eat();
    println!(
        "{} {}",
        Rc::strong_count(&heap_pie2),
        Rc::weak_count(&heap_pie2)
    );
}
```

## Ref Cell
스마트 포인터가 보유하는 컨테이너 구조이다. 
- 데이터를 가져오거나 안에 있는 것에 대한 mutable 또는 immutable borrowing이 가능하다.
- borrowing할 때 러스트는 런탐이에 메모리 안전 규칙을 적용해 남용을 방지한다. 이 규칙을 어기면 RefCell은 panic을 일으킨다. 

```rust
use std::cell::RefCell;

struct Pie {
    slices: u8,
}

impl Pie {
    fn eat(&mut self) {
        println!("Pie eaten!");
        self.slices -= 1;
    }
}

fn main() {
    let pie_cell = RefCell::new(Pie { slices: 8 });

    {
        let mut pie = pie_cell.borrow_mut();
        pie.eat();
        pie.eat();
    }

    let ref_pie = pie_cell.borrow();
    println!("{} slices left", ref_pie.slices);
}
```

## 스레드 간에 공유하기 Mutex
Mutex는 보통 스마트 포인터가 보유하는 컨테이너 데이터 구조로서, 데이터를 가져오가나 안에 있는 것에 대한 immutable, mutable borrowing을 해준다. 
- 비선점 구조로 잠긴 대여를 통해 하나의 CPU만 접근 가능하게 한다.
- Arc: 스레드 안정성을 가진 참조 카운트 증가 방식을 사용한다. 그외엔 Rc랑 동일하다. 
```rust 
use std::sync::Mutex;

struct MPie;
impl MPie {
    fn eat(&self) {
        println!("only I get. Pie eaten!");
    }
}

fn main() {
    println!("Mutex ---- ");
    let pie_mutex = Mutex::new(MPie);

    let m_pie = pie_mutex.lock().unwrap();
    m_pie.eat();
```

## 스마트 포인터 조합하기
Rc<Vec<Foo>>
: 힙에 있는 immutable한 데이터 구조의 동일한 Vec을 대여할 수 있는 복수 스마트 포인터를 복제(clone)가능하게 한다. 

Rc<RefCell<Foo>>
: 복수의 스마트 포인터가 동일한 Foo 구조체를 mutable 또는 immutable하게 대여할 수 있게 한다. 

Arc<Mutex<Foo>>
: 복수의 스마트 포인터가 임시의 mutable 또는 immutable 대여를 CPU 스레드 독점 방식으로 lock할 수 있게 한다. 

```rust 
use std::cell::RefCell;
use std::rc::Rc;

struct Pie {
    slices: u8,
}

impl Pie {
    fn eat_slice(&mut self, name: &str) {
        println!("{} eats a slice of pie", name);
        self.slices -= 1;
    }
}

struct SeaCreature {
    pub name: String,
    pie: Rc<RefCell<Pie>>,
}

impl SeaCreature {
    fn eat(&self) {
        let mut pie = self.pie.borrow_mut();
        pie.eat_slice(&self.name);
    }
}

fn main() {
    let pie = Rc::new(RefCell::new(Pie { slices: 8 }));
    let ferris = SeaCreature {
        name: String::from("Ferris"),
        pie: pie.clone(),
    };

    let sarah = SeaCreature {
        name: String::from("Sarah"),
        pie: pie.clone(),
    };

    ferris.eat();
    sarah.eat();

    let p = pie.borrow();
    println!("{} slices of pie left", p.slices);
}

```

<br>

# 멀티 스레딩 기법
## spawn으로 새로운 스레드 생성 
thread::spawn 함수를 호출하여 여기에 새로운 스레드에서 실행하고 싶은 코드가 담긴 클로저를 넘긴다. 해당 메서드는 JoinHandle<T>를 리턴한다. 
```rust
use std::thread;
use std::time::Duration;

fn main() {
    thread::spawn(|| {
        for i in 1..10 {
            println!("hi number {} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(1));
        }
    });

    for i in 1..5 {
        println!("hi number {} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }
}
```

## join 핸들을 사용하여 모든 스레드가 끝날 때까지 blocking하기 
spawn메서드가 리턴한 JoinHandle은 자신의 join 메서드를 호출했을 때 그 스레드가 끝날 때까지 기다리는 소윳값이다. 
```rust
use std::thread;
use std::time::Duration;

fn main() {
    let handle = thread::spawn(|| {
        for i in 1..10 {
            println!("hi number {} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(1));
        }
    });

    for i in 1..5 {
        println!("hi number {} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }

    handle.join().unwrap();
}
```

## move 클로저 
move 클로저는 thread::spawn에 넘겨지는 클로저와 함께 자주 사용된다. 
클로저가 환경으로부터 사용하는 값 소유권을 갖게 된다. 
```rust
let v = vec![1, 2, 3];

let handle = thread::spawn(move || {
    println!("Here's a vector: {:?}", v);
});

// drop(v); // error[E0382]: use of moved value: `v`

handle.join().unwrap();
```

# Resources
- https://academy.terra.money/courses/rust-basics
- https://rust-kr.github.io/doc.rust-kr.org
