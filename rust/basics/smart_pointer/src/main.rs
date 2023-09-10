use std::cell::RefCell;
use std::rc::Rc;
// use std::sync::Mutex;

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
