package ch4.item23.tagclass;

public class Practice {

	public static void main(String[] args) {
		Figure circle = new Figure(2);
		Figure rectangle = new Figure(2,2);
		Figure random = new Figure();
		System.out.println(circle.area());
		System.out.println(rectangle.area());
	}
}
