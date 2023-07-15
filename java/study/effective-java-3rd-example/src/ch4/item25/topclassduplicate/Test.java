package ch4.item25.topclassduplicate;

public class Test {
	public static void main(String[] args) {
		System.out.println(Utensil.NAME + Dessert.NAME);
	}

	private static class Utensil{
		static final String NAME = "pan";
	}

	private static class Dessert{
		static final String NAME = "cake";
	}
}
