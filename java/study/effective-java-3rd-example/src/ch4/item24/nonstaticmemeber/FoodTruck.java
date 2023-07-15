package ch4.item24.nonstaticmemeber;

public class FoodTruck {
	private final String name;

	public FoodTruck(String name) {
		this.name = name;
	}

	// 비정적 멤버 클래스와 바깥 클래스 연관 관계
	public void orderPizza(String pizzaName) {
		Pizza pizza = new Pizza(pizzaName);
		pizza.printOrderStatus();
	}

	public String getName() {
		return name;
	}

	private class Pizza {
		private final String pizzaName;

		public Pizza(String pizzaName) {
			this.pizzaName = pizzaName;
		}

		public void printOrderStatus() {
			// 정규화된 this - 바깥 클래스의 인스턴스 메서드 사용
			System.out.println("["+ FoodTruck.this.getName()+ " truck] order success: "+ pizzaName +" pizza");
		}
	}

	public static void main(String[] args) {
		FoodTruck foodTruck = new FoodTruck("loosie");
		foodTruck.orderPizza("combination");
	}
}
