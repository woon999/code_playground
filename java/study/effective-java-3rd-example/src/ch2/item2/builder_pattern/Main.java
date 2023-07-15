package ch2.item2.builder_pattern;

public class Main {
    public static void main(String[] args) {
        NutritionFacts3 cocaCola = new NutritionFacts3.Builder(240,8)
                .calories(100).sodium(35).carbohydrate(27).build();

        System.out.println("cocaCola = " + cocaCola.toString());

    }
}
