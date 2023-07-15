package EnumEx;

abstract class MyEnum<T extends  MyEnum<T>> implements Comparable<T>{
    static int id = 0;
    int oridinal;
    String name = "";

    public int oridinal(){ return oridinal; }

    MyEnum(String name){
        this.name = name;
        oridinal = id++;
    }

    public int compareTo(T t){
        return oridinal - t.oridinal();
    }
}

abstract class MyTransportation extends MyEnum {
   static final MyTransportation BUS = new MyTransportation("BUS", 100) {
       int fare(int distance) { return distance - BASIC_FARE; }
   };
    static final MyTransportation TRAIN = new MyTransportation("TRAIN", 150) {
        int fare(int distance) { return distance - BASIC_FARE; }
    };
    static final MyTransportation SHIP = new MyTransportation("SHIP", 100) {
        int fare(int distance) { return distance - BASIC_FARE; }
    };
    static final MyTransportation AIRPLANE = new MyTransportation("AIRPLANE", 300) {
        int fare(int distance) { return distance - BASIC_FARE; }
    };

    abstract int fare(int distance); // 거리에 따른 요금을 계산하는 추상 메서드
    protected final int BASIC_FARE; // protected로 해야 각 상수에서 접근 가능
    private MyTransportation(String name, int basicFare){
        super(name);
        BASIC_FARE = basicFare;
    }

    public String name() { return name; }
    public String toString() { return name; }

}

public class EnumEx4 {

    public static void main(String[] args) {
        MyTransportation t1 = MyTransportation.BUS;
        MyTransportation t2 = MyTransportation.BUS;
        MyTransportation t3 = MyTransportation.SHIP;
        MyTransportation t4 = MyTransportation.AIRPLANE;
        MyTransportation t5 = MyTransportation.AIRPLANE;

        System.out.printf("t1=%s, %d%n", t1.name(), t1.oridinal());
        System.out.printf("t2=%s, %d%n", t2.name(), t2.oridinal());
        System.out.printf("t3=%s, %d%n", t3.name(), t3.oridinal());
        System.out.printf("t4=%s, %d%n", t4.name(), t4.oridinal());
        System.out.printf("t5=%s, %d%n", t5.name(), t5.oridinal());
        System.out.println("t1==t2 ? "+ (t1==t2));
        System.out.println("t1.compareTo(t3)="+ t1.compareTo(t3));

    }
}
