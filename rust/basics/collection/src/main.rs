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

fn main() {
    vector();
    // hashMap();
}
