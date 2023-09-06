fn give(drink: Option<&str>) {
    match drink {
        Some(inner) => println!("{}? How nice.", inner),
        None => println!("No drink?! Oh well."),
    }
}

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

fn main() {
    give(Some("water"));
    give(None);
    
    let nothing = None;
    give_vegan(nothing);
    give_vegan(Some("water"));
    // give_vegan(Some("milk"));
}