use std::sync::{Arc, Mutex};
use std::thread;

pub fn mutex() {
    let m = Mutex::new(5);

    {
        let mut num = m.lock().unwrap(); // mutex에 접근하기 위해서 lock()을 호출해야 한다.
        *num = 6;
    }

    println!("m = {:?}", m);
}

pub fn share_mutex() {
    let counter = Arc::new(Mutex::new(0));
    let mut handles = vec![];

    for _ in 0..10 {
        // 소유권 이전을 위해 클로저를 사용한다. 그리고 clone을 통해 counter의 소유권을 복사한다.
        let counter = Arc::clone(&counter);
        let handle = thread::spawn(move || {
            let mut num = counter.lock().unwrap();

            *num += 1;
        });
        handles.push(handle);
    }

    for handle in handles {
        handle.join().unwrap();
    }

    println!("Result: {}", *counter.lock().unwrap());
}
