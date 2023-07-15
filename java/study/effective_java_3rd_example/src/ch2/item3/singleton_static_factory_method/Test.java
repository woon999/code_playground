package ch2.item3.singleton_static_factory_method;

import ch2.item3.SerializationTester;

public class Test extends SerializationTester {
    public static void main(String[] args) {
        Elvis instance = Elvis.getInstance();
        Elvis result = (Elvis)deserialize(serialize(instance));

        System.out.println("instance == result : " + (instance == result));
        System.out.println("instance.equals(result) : " + (instance.equals(result)));
    }
}
