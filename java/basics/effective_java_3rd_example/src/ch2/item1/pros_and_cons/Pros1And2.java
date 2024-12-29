package ch2.item1.pros_and_cons;

import java.util.HashMap;
import java.util.Map;

import static ch2.item1.pros_and_cons.Type.*;

enum Type{
    NORMAL, ELECTRONIC, HYBRID, STEEL;
}

class CarNumber{
    private static Map<Integer, CarNumber> numberCache = new HashMap<>();
    private static final int MIN_CAR_NUMBER = 1;
    private static final int MAX_CAR_NUMBER = 10;

    private int number;
    private CarNumber(int number){
        this.number = number;
    }

    public static CarNumber of() {
        if(numberCache.size() == MAX_CAR_NUMBER) {
            throw new RuntimeException("모든 번호가 사용되고 있습니다.");
        }

        int num=(int)(Math.random()*MAX_CAR_NUMBER+1);
        while (num >= MIN_CAR_NUMBER && numberCache.containsKey(num)) {
            num = (int) (Math.random() * MAX_CAR_NUMBER + 1);
        }

        numberCache.put(num, new CarNumber(num));
        return numberCache.get(num);
    }

    public static CarNumber of(int number) {
        if(numberCache.size() == MAX_CAR_NUMBER) {
            throw new RuntimeException("모든 번호가 사용되고 있습니다.");
        }

        if(numberCache.containsKey(number)){
            throw new RuntimeException(number+" 번호는 이미 사용되고 있습니다.");
        }
        numberCache.put(number, new CarNumber(number));
        return numberCache.get(number);
    }

    @Override
    public String toString() {
        return ""+number;
    }
}

class Car{
    private CarNumber carNumber;
    private Type type;

    public Car(Type type){
        this.type = type;
    }

    public void setAutoCarNumber(){
        this.carNumber = CarNumber.of();
    }
    public void setCarNumber(int num){
        this.carNumber = CarNumber.of(num);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carNumber=" + carNumber +
                ", type=" + type +
                '}';
    }
}

class CarFactory {
    private static final Map<Type, Car> map = new HashMap<>();
    private static final Type DEFAULT_TYPE = NORMAL;
    public static Car createDefaultCar(){
        return getCarInstance(DEFAULT_TYPE);
    }
    public static Car createCustomizingCar(Type type){
        return getCarInstance(type);
    }

    private static Car getCarInstance(Type type){
        Car car = map.get(type);
        // 색이 등록 안되어 있을 경우 인스턴스 생성
        if(car == null) {
            car = new Car(type);
            map.put(type, car);
            System.out.println("(new) 새로운 차 타입 생성 : " + type);
        }
        return car;
    }

}
public class Pros1And2 {

    public static void main(String[] args) {
        // 장점1. 이름을 가질 수 있다.
        Car car1 = CarFactory.createDefaultCar();
        car1.setAutoCarNumber();
        System.out.println("car1 = " + car1);
        Car car2 = CarFactory.createCustomizingCar(ELECTRONIC);
        car2.setAutoCarNumber();
        System.out.println("car2 = " + car2);

        // 장점 2. 호출할 때마다 인스턴스를 새로 생성하지 않아도 된다.
        Type[] types = {NORMAL, ELECTRONIC, HYBRID, STEEL};
        for(int i=0; i<8; i++) {
            Car car = CarFactory.createCustomizingCar(types[(int)(Math.random()*4)]);
            car.setAutoCarNumber();
            System.out.println("car = " + car);
        }

        // Runtime Error 발생
//        Car car3 = CarFactory.createDefaultCar();
//        car3.setCarNumber(1);



    }
}
