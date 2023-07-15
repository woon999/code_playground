package GenericEx;

import java.util.ArrayList;

class Fruit {
    public String toString() {
        return "GenericEx.Fruit";
    }
}
class Apple extends Fruit {
    public String toString() {
        return "GenericEx.Apple";
    }
}
class Grape extends Fruit {
    public String toString() {
        return "GenericEx.Grape";
    }
}
class Juice {
    String name;
    Juice(String name) {
        this.name = name + "GenericEx.Juice";
    }
    public String toString() {
        return name;
    }
}
class Juicer{
    static Juice makeJuice(FruitBox<? extends Fruit> box){
        String tmp ="";
        for(Fruit f : box.getList()) tmp += f +" ";
        return new Juice(tmp);
    }
}

public class GenericsEx3 {
    public static void main(String[] args) {
        FruitBox<Fruit> fruitBox = new FruitBox<>();
        FruitBox<Apple> appleBox = new FruitBox<>();

        fruitBox.add(new Apple());
        fruitBox.add(new Grape());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        System.out.println(Juicer.makeJuice(fruitBox));
        System.out.println(Juicer.makeJuice(appleBox));
    }
}

class FruitBox<T extends Fruit> extends Box<T>{}

class Box<T> {
    ArrayList<T> list = new ArrayList<>();
    void add(T item) { list.add(item); }
    T get (int i) { return list.get(i); }
    ArrayList<T> getList() { return list; }
    int size() { return list.size(); }
    public String toString() { return list.toString(); }
}
