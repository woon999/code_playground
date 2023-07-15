package ch2.item1.pros_and_cons;

import java.util.*;

// 장점 3. 반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.
// 장점 4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.
class A{
}
class B extends A{
    B(){
        System.out.println("B class");
    }
}
class C extends A{
    C(){
        System.out.println("C class");
    }
}

class AFactory{
    private static Map<Integer, A> map = new HashMap<>();
     static A getInstance(int type){
         A newInstance = map.get(type);
         if(newInstance == null) {
             if (type == 1) { // type ==1, B 클래스 반환
                 newInstance = new B();
             } else { // type == 2, C 클래스 반환
                 newInstance = new C();
             }
             map.put(type, newInstance);
         }
        return newInstance;
    }
}

public class Pros3And4 {
    public static void main(String[] args) {
        A test1 = AFactory.getInstance(1);
        System.out.println("test1 = " + test1);
        A test2 = AFactory.getInstance(2);
        System.out.println("test2 = " + test2);

        A test3 = AFactory.getInstance(1);
        System.out.println(test3==test1);
    }
}
