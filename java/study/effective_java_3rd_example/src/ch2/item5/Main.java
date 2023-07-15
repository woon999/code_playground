package ch2.item5;

import static ch2.item5.fatctory_method_pattern.Pizza.*;
import static ch2.item5.fatctory_method_pattern.Pizza.PizzaType.*;


import ch2.item5.fatctory_method_pattern.Pizza;

public class Main {
	public static void main(String[] args) {

		SpellChecker spellChecker = new SpellChecker(new Lexicon());
		System.out.println(spellChecker.isValid("apple"));
		System.out.println(spellChecker.isValid("banana"));
		System.out.println(spellChecker.isValid("P12"));

		Pizza pizza1 = PizzaFactory(HamMushroom);
		System.out.println("pizza1 = " + pizza1.getPrice());
		Pizza pizza2 = PizzaFactory(Deluxe);
		System.out.println("pizza2 = " + pizza2.getPrice());
		Pizza pizza3 = PizzaFactory(Seafood);
		System.out.println("pizza3 = " + pizza3.getPrice());

	}
}
