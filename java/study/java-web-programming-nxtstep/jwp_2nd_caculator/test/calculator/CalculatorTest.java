package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	private Calculator cal;
	@BeforeEach
	public void setup(){
		cal = new Calculator();
		System.out.println("before");
	}

	@AfterEach
	public void teardown(){
		System.out.println("teardown");
	}

	@Test
	public void add(){
		assertEquals(cal.add(6, 3), 9);
	}

	@Test
	public void subtract(){
		assertEquals(cal.subtract(6, 3), 3);
	}

	@Test
	public void multiply(){
		assertEquals(cal.multiply(6, 3), 18);
	}

	@Test
	public void divide(){
		assertEquals(cal.divide(6, 3), 2);
	}

}