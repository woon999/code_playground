package ch4.item23.tagclass;

public class Figure {
	enum Shape {RECTANGLE, CIRCLE, SQUARE, RANDOM }

	// 태그 필드 - 현재 모양을 나타낸다.
	private final Shape shape;

	// RECTANGLE 필드
	private double length;
	private double width;

	// CIRCLE 필드
	private double radius;

	public Figure() {
		shape = Shape.RANDOM;
	}

	// CIRCLE 생성자
	public Figure(double radius) {
		shape = Shape.CIRCLE;
		this.radius = radius;
	}

	// RECTANGLE 생성자
	public Figure(double length, double width) {
		if (length == width) {
			shape = Shape.SQUARE;
		} else {
			shape = Shape.RECTANGLE;
		}
		this.length = length;
		this.width = width;
	}


	public double area() {
		switch (shape) {
			case RECTANGLE: case SQUARE:
				return length * width;
			case CIRCLE:
				return Math.PI * (radius * radius);
			default:
				throw new AssertionError(shape);
		}
	}

	public static void main(String[] args) {
		Figure circle = new Figure(2);
		Figure rectangle = new Figure(1,2);
		Figure random = new Figure();

		System.out.println(circle.area());
		System.out.println(rectangle.area());

		Figure square = new Figure(2, 2);
		System.out.println(square.area());
	}
}
