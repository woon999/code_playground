package ch6.ingredient;

public class Dish {
	private String menuName;
	private boolean isVegetarian;
	private int calories;
	private Type type;

	public Dish(String menuName, boolean isVegetarian, int calories, Type type) {
		this.menuName = menuName;
		this.isVegetarian = isVegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getMenuName() {
		return menuName;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return menuName;
	}
}

