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

# Resources
- https://academy.terra.money/courses/rust-basics
- https://rust-kr.github.io/doc.rust-kr.org
