package ch2.item5.fatctory_method_pattern;

public abstract class Pizza {
	public abstract int getPrice();

	public enum PizzaType{
		HamMushroom, Deluxe, Seafood
	}

	public static final Pizza PizzaFactory(PizzaType pizzaType) {
		switch (pizzaType){
			case HamMushroom :
				return new HamMushroomPizza();
			case Deluxe :
				return new DeluxePizza();
			case Seafood :
				return new SeafoodPizza();
		}
		throw new RuntimeException("The pizza type " +
			pizzaType.toString() + "  is not Recognized.");
	}

}

class HamMushroomPizza extends Pizza {
	private int price = 15_000;
	@Override public int getPrice() {
		return price;
	}
}
class DeluxePizza extends Pizza {
	private int price = 20_000;
	@Override public int getPrice() {
		return price;
	}
}
class SeafoodPizza extends Pizza {
	private int price = 23_000;
	@Override public int getPrice() {
		return price;
	}
}