package ch2.item1.flyweight_pattern;

import java.util.HashMap;
import java.util.Map;

public class ShapeFactory {
    private static final Map<Color, Circle> map = new HashMap<>();

    // 장점 2. 호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.
    public static Shape getInstance(Color color){
        Circle circle = map.get(color);

        // 색이 등록 안되어 있을 경우 인스턴스 생성
        if(circle == null) {
            circle = new Circle(color);
            map.put(color, circle);
            System.out.println("(new) 새로운 객체 생성 : " + color);
        }
        return circle;
    }
}
