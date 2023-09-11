mod multi;
use multi::{multi_threading, multi_threading_with_move_closure};

mod mp;
use mp::message_passing_mpsc;

mod mutex;
use mutex::{mutex, share_mutex};

fn main() {
    // multi_threading();
    // multi_threading_with_move_closure();

    // message_passing_mpsc();

    // mutex();
    share_mutex();
}
