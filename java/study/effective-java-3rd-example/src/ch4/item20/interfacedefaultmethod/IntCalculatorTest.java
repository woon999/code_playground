package ch4.item20.interfacedefaultmethod;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IntCalculatorTest {

	@Test
	void calculator_test(){
		Calculator calculator = new IntCalculator();
		calculator.print(100, 200);
		Object plus = calculator.plus(1, 2);
		Object minus = calculator.minus(2, 3);
		Object divide = calculator.divide(4, 2);
		Object multiple = calculator.multiple(5, 2);

		System.out.println("plus = " + plus);
		System.out.println("minus = " + minus);
		System.out.println("divide = " + divide);
		System.out.println("multiple = " + multiple);

		assertEquals(plus, 3);
		assertEquals(minus, -1);
		assertEquals(divide, 2);
		assertEquals(multiple, 10);


	}
}