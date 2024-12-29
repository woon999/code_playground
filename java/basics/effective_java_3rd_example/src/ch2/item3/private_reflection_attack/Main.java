package ch2.item3.private_reflection_attack;

import ch2.item3.singleton_public_staic_fianl_field.Elvis;
import java.lang.reflect.Constructor;

public class Main {
    public static void main(String[] args) throws Exception{
        Elvis obj1 = Elvis.INSTANCE;

        Constructor<Elvis> constructor = Elvis.class.getDeclaredConstructor(Access.class);
        constructor.setAccessible(true);
        Elvis obj2 = constructor.newInstance(Access.REFLECTION);
        constructor.setAccessible(false);
        obj1.leaveTheBuilding();
        obj2.leaveTheBuilding();
        System.out.println("Both Objs are equal :" + obj1.equals(obj2));
        System.out.println("hashcode of obj1 :" + obj1.hashCode());
        System.out.println("hashcode of obj2 :" + obj2.hashCode());
    }
}
