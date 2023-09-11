use std::thread;
use std::time::Duration;

fn use_move() {
    let v = vec![1, 2, 3];

    let handle = thread::spawn(move || {
        println!("Here's a vector: {:?}", v);
    });

    // drop(v); // error[E0382]: use of moved value: `v`

    handle.join().unwrap();
}

fn main() {
    use_move();

    // spawn으로 새 스레드 생성
    let handle = thread::spawn(|| {
        for i in 1..10 {
            println!("hi number {} from the spawned thread!", i);
            thread::sleep(Duration::from_millis(1));
        }
    }); // return JoinHandle<T>

    // 1..5: 1<= x < 5
    for i in 1..5 {
        println!("hi number {} from the main thread!", i);
        thread::sleep(Duration::from_millis(1));
    }

    handle.join().unwrap(); // spawn된 스레드 종료 대기(blocking)

    println!("main thread done");
}
