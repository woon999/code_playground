package ch2.item2.java_beans_pattern;

public class Main {
    public static void main(String[] args) {
        NutritionFacts2 cocaCola = new NutritionFacts2();
        cocaCola.setServingSize(240);
        cocaCola.setServings(8);
        cocaCola.setCalories(100);
        cocaCola.setSodium(35);
        cocaCola.setCarbohydrate(27);

        System.out.println("cocaCola = " + cocaCola.toString());
    }
}
