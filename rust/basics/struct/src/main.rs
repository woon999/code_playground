struct User {
    active: bool,
    username: String,
    email: String,
    sign_in_count: u64,
}

fn build_user(email: String, username: String) -> User {
    User {
        active: true,
        username,
        email,
        sign_in_count: 1,
    }
}

struct Point(i32, i32, i32);

fn main() {
    let mut user1 = User {
        active: true,
        username: String::from("someusername123"),
        email: String::from("someone@example.com"),
        sign_in_count: 1,
    };
    user1.email = String::from("anotheremail@example.com");
    println!("user1.email = {}", user1.email);

    let user2 = build_user(String::from("email"), String::from("username"));
    println!("user2.email = {}", user2.email);

    let origin = Point(0, 1, 0);
    println!("origin.1 = {}", origin.1);
}