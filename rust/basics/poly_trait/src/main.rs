struct SeaCreature {
    pub name: String,
    noise: String,
}

impl SeaCreature {
    pub fn get_sound(&self) -> &str {
        &self.noise
    }
}

trait NoiseMaker {
    fn make_noise(&self);
}

// 트레잇 상속
trait LoudNoiseMaker: NoiseMaker {
    fn make_alot_of_noise(&self) {
        self.make_noise();
        self.make_noise();
        self.make_noise();
    }
}

impl NoiseMaker for SeaCreature {
    fn make_noise(&self) {
        println!("{}", &self.get_sound());
    }
}

impl LoudNoiseMaker for SeaCreature {}

fn static_make_noise(creature: &SeaCreature) {
    creature.make_noise();
}

fn dynamic_make_noise(creature: &dyn NoiseMaker) {
    creature.make_noise();
}

fn generic_make_noise<T: NoiseMaker>(creature: &T)
where
    T: NoiseMaker,
{
    creature.make_noise();
}

fn main() {
    let creature = SeaCreature {
        name: String::from("Ferris"),
        noise: String::from("blub"),
    };

    // creature.make_alot_of_noise();
    // static_make_noise(&creature);
    // dynamic_make_noise(&creature);

    generic_make_noise(&creature);
}
