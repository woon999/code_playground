package decorator;

public class StarbuzzCoffee{

	public static void main(String[] args) {
		Beverage beverage = new Espresso();
		printBeverage(beverage);

		Beverage beverage2 = new HouseBlend();
		beverage2 = new Mocha(beverage2);
		printBeverage(beverage2);
	}

	public static void printBeverage(Beverage beverage){
		System.out.println("beverage = " + beverage.getDescription() +" $" + beverage.cost());
	}
}
