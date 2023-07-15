package ch2.item1.flyweight_pattern;

public class Circle implements Shape {
    private Color color;
    private int x,y,radius;

    public Circle(Color color) {
        this.color = color;
    }

    public void setVariables(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    @Override
    public void draw() {
        System.out.println("Circle [color = " + color +
                            ", x = " + x +
                            ", y = " + y +
                            ", radius = " + radius +
                            "] --- " + Integer.toHexString(System.identityHashCode(this))
        );
    }
}
