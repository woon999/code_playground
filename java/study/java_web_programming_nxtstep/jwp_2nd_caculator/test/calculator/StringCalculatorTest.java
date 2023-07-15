package calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

	private StringCalculator cal;

	@BeforeEach
	public void setup(){
		cal = new StringCalculator();
	}

	@Test
	public void default_add(){
		String[] inputs = {"1:2,3", "1,2,3", "1:2:3"};
		Arrays.stream(inputs).forEach(input -> assertEquals(cal.add(input), 6));
	}

	@Test
	public void emptyString_add(){
		assertEquals(cal.add(null), 0);
		assertEquals(cal.add(""), 0);

		String[] inputs = {":2,4", "::6"};
		Arrays.stream(inputs).forEach(input -> assertEquals(cal.add(input), 6));
	}

	@Test
	public void custom_add(){
		String[] inputs = {"//@\n2@4", "//;\n1;2;3", "//;\n1;2:3"};
		Arrays.stream(inputs).forEach(input -> assertEquals(cal.add(input), 6));
	}

	@Test
	public void exception_test() throws RuntimeException{
		String input = "-1";
		assertThrows(RuntimeException.class, () -> {
			cal.add(input);
		});
	}
}