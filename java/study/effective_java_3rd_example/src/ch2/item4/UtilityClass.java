package ch2.item4;

public class UtilityClass {

	private UtilityClass() {
		System.out.println("private Constructor");
		throw new AssertionError();
	}

	public static void print() {
		System.out.println("Util.print()");
	}

	public static void add(int a, int b) {
		System.out.println(a + "+" + b + " = " + (a + b));
	}

}
