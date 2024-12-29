package ch2.item1.pros_and_cons;


// 장점 5. 정적 팩토리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.
class StaticFactoryChild extends StaticFactory{
    StaticFactoryChild(){
        System.out.println("정상 로드 되었습니다.");
    }

    public void getName(){
        System.out.println("StaticFactoryChild 입니다.");
    }
}
class StaticFactory{
    public void getName(){};
    static StaticFactory getNewInstance(){
        StaticFactory tmp = null;
        try{
            Class<?> childClass = Class.forName("ch2.item1.pros_and_cons.StaticFactoryChild");
            tmp = (StaticFactory)childClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return tmp;
    }
}
public class Pros5 {
    public static void main(String[] args) {
        StaticFactory staticFactory = StaticFactory.getNewInstance();
        staticFactory.getName();
        System.out.println(staticFactory.getClass().getTypeName());
    }
}
