package ch2.item2.builder_pattern_ex;

import static ch2.item2.builder_pattern_ex.NyPizza.Size.*;
import static ch2.item2.builder_pattern_ex.Pizza.Topping.*;

public class Main {
    public static void main(String[] args) {
        NyPizza ny = new NyPizza.Builder(SMALL)
                .addTopping(SAUSAGE).addTopping(ONION).build();
        CalzonePizza cal = new CalzonePizza.Builder()
                .addTopping(HAM).sauceInside().build();
        System.out.println("ny = " + ny);
        System.out.println("cal = " + cal);
    }
}
