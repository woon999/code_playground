// 1. Mutuability
const GLOBAL_CONSTANT: i32 = 5;
fn mutuability(){
    // mutable
    let mut x = 5;
    println!("The value of x is: {}", x); // 5

    x = x+1;
    println!("The value of x is: {}", x); // 6

    // immutable
    let y = 5;
    // y = y+1; // error: cannot assign twice to immutable variable `y`
    println!("The value of y is: {}", y); // 5
    
    let y = y+1; // shadowing
    println!("The value of y is: {}", y); // 6

    // constants must declare types 
    const Z: i32 = 5;
    println!("The value of GLOBAL_CONSTANT is: {}", GLOBAL_CONSTANT); // 5
    println!("The value of Z is: {}", Z); // 5
}

// 2. Ownership
fn ownership(){
    let mut vec = Vec::new();
    vec.push(1);
    vec.push(2);
    take(vec); // 소유권 넘김
    // vec.push(3); // error: borrow of moved value: `vec`

    let x = 5;
    copy(x); // 복사 
    println!("out x = {}", x); // 5

    // heap에 있는 데이터는 소유권이 넘어감
    let s1 = String::from("hello");
    let s2 = s1; // s1의 소유권이 s2로 넘어감. s1 사용 불가
    // println!("{}, world!", s1); // error[E0382]: borrow of moved value: `s1

    // stack에 있는 데이터는 복사됨
    let q = 5;
    let w = q;
    println!("q = {}, w = {}", q, w);
}

fn take(vec: Vec<i32>){ // vec 소유권 받음 
    println!("vec[0] = {}", vec[0]);
} // drop 호출. 메모리 해제 

fn copy(x: i32){ // x 복사
    println!("in x = {}", x);
} // 아무 일도 발생하지 않음 

// 3. Borrowing
fn borrowing(){
    let mut vec = Vec::new();
    vec.push(1);
    vec.push(2);
    user(&vec); // 소유권 빌려주기
    vec.push(3); 
    println!("vec[2] = {}", vec[2]);

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


    let mut ss = String::from("hello");
    let rr1 = &ss; // 문제 없음
    let rr2 = &ss; // 문제 없음
    let rr3 = &mut ss; // 큰 문제
    // println!("{}, {}, and {}", rr1, rr2, rr3); // error[E0502]: cannot borrow `ss` as mutable because it is also borrowed as immutable


    // dangle
    // let reference_to_nothing = dangle(); // error[E0106]: missing lifetime specifier

    // no dangle
    let reference_to_nothing = no_dangle();
    println!("reference_to_nothing = {}", reference_to_nothing);
}

// shared reference(&) to vec
fn user(vec: &Vec<i32>){ // vec은 &vec을 참조한다 
    println!("vec[0] = {}", vec[0]);
} // 갖고있는 소유권이 없기 때문에 아무일도 발생하지 않음 

fn change(some_string: &mut String) {
    some_string.push_str(", world");
}

// fn dangle() -> &String{ // String의 참조자를 반환
//     let s = String::from("hello");
//     &s // s의 참조자를 반환
// } // s는 스코프 밖으로 벗어나고 drop 호출. 메모리 해제. 위험하다 

fn no_dangle() -> String{ // String을 반환
    let s = String::from("hello");
    s // s의 소유권을 반환
} // s는 스코프 밖으로 벗어나고 drop 호출. 메모리 해제. 안전하다

fn slice(){
    let s = String::from("hello world");

    let hello = &s[0..5];
    let world = &s[6..11];

    println!("hello = {}, world = {}", hello, world);

    let mut ss = String::from("hello world");
    let word = first_word(&ss);
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

fn main() {
    // mutuability();
    // ownership();
    // borrowing(); 

    slice();
}