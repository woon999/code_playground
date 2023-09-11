use std::sync::mpsc;
use std::thread;
use std::time::Duration;

// 스레드간 통신 with mpsc(sender, receiver) 채널
pub fn message_passing_mpsc() {
    let (sender, receiver) = mpsc::channel();

    let sender2 = sender.clone();
    thread::spawn(move || {
        let vals = vec![
            String::from("hi"),
            String::from("from"),
            String::from("the"),
            String::from("thread"),
        ];

        for val in vals {
            sender2.send(val).unwrap();
            thread::sleep(Duration::from_secs(1));
        }
    });

    thread::spawn(move || {
        let vals = vec![
            String::from("more"),
            String::from("messages"),
            String::from("for"),
            String::from("you"),
        ];

        for val in vals {
            sender.send(val).unwrap();
            thread::sleep(Duration::from_secs(1));
        }
    });

    for received in receiver {
        println!("Got: {}", received);
    }
}
