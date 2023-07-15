package ch4.item20.simulatedmultipleinheritance;

import static ch4.item20.simulatedmultipleinheritance.Food.*;

enum Food{
	FOOD;
}

class Truck{
	public void drive(String to){
		System.out.println("to = " + to);
	}
}

interface Kitchen{
	void cook(Food food);
	void turnOn();
	void turnOff();
}
abstract class AbstractKitchen implements Kitchen{
	@Override public void turnOn() {
		System.out.println("turn on");
	}

	@Override public void turnOff() {
		System.out.println("turn off");
	}
}

public class PizzaTruck extends Truck implements Kitchen{
	private final InnerKitchen innerKitchen = new InnerKitchen();

	@Override public void cook(Food food) {
		innerKitchen.cook(food);
	}

	@Override public void turnOn() {
		innerKitchen.turnOn();
	}

	@Override public void turnOff() {
		innerKitchen.turnOff();
	}

	private class InnerKitchen extends AbstractKitchen{
		@Override public void cook(Food food) {
			System.out.println("pizza truck cook  = " + food);
		}
	}

	public static void main(String[] args) {
		PizzaTruck pizzaTruck = new PizzaTruck();
		pizzaTruck.cook(FOOD);
		pizzaTruck.turnOn();
		pizzaTruck.turnOff();
	}
}
