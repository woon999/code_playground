package ch2.item1.flyweight_pattern;


import static ch2.item1.flyweight_pattern.Color.*;

public class Main {
    public static void main(String[] args) {
        Color[] colors = {BLACK, BLUE, WHITE, YELLOW};

        for(int i=0; i<10; i++){
            Circle circle = (Circle)ShapeFactory.getInstance(colors[(int)(Math.random()*4)]);
            circle.setVariables(
                    (int)(Math.random()*100),
                    (int)(Math.random()*100),
                    (int)(Math.random()*10)
            );
            circle.draw();
        }
    }
}