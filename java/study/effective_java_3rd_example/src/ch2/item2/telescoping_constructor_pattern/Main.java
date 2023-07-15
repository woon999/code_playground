package ch2.item2.telescoping_constructor_pattern;

public class Main {
    public static void main(String[] args) {
        NutritionFacts cocaCola = new NutritionFacts(240, 8, 100, 0, 35 ,27);
        System.out.println("cocaCola = " + cocaCola.toString());
    }
}
