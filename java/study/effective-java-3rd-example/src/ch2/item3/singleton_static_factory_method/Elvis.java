package ch2.item3.singleton_static_factory_method;

import java.io.Serializable;
// 정적 팩토리 방식의 싱글턴
public class Elvis implements Serializable {
    private static final Elvis INSTANCE = new Elvis();
    public static int count=0;
    private Elvis(){}

    public static Elvis getInstance(){
        count++;
        return INSTANCE;
    }

    // 싱글턴임을 보장해주는 readSolve 메서드
    private Object readResolve(){
        // '진짜' Elvis를 반환하고, 가짜 Elvis는 가비지 컬렉터에 맡긴다.
        return INSTANCE;
    }

    public void leaveTheBuilding(){
        System.out.println(INSTANCE + ", Elvis leaveTheBuilding" + count);
    }
}
