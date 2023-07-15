package ch4.item23.hierarchyclass;


public class Practice {

	public static void main(String[] args) {
		Circle circle = new Circle(2);
		Rectangle rectangle = new Rectangle(2,2);
		System.out.println(circle.area());
		System.out.println(rectangle.area());

		Rectangle square = new Square(2);
		System.out.println(square.area());
	}
}
