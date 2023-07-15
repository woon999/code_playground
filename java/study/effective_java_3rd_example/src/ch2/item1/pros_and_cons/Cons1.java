package ch2.item1.pros_and_cons;

// error. public 디폴트 생성자 필요
// class Poo extends Factory{ }
class Factory{
    private Factory(){
    }

    static void getInstance(){
        // ...
    }

}

public class Cons1 {
    public static void main(String[] args) {
        Factory.getInstance();
    }
}
