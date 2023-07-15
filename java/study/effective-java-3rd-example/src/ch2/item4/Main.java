package ch2.item4;

import java.lang.reflect.Constructor;

public class Main {
	public static void main(String[] args) throws Exception{

		// UtilityClass u = new UtilityClass(); // compile error

		// runtime error. AssertionError
		Constructor<UtilityClass> constructor = UtilityClass.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		UtilityClass instance = constructor.newInstance();

		UtilityClass.print();
		UtilityClass.add(1, 2);
	}
}
