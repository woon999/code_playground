package hashset;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashSetTester {

	HashSet<Integer> intSet = new HashSet<>();

	@Test
	void addTest(){
		boolean one = intSet.add(1);
		boolean two = intSet.add(2);
		boolean againOne = intSet.add(1);

		assertEquals(one, true);
		assertEquals(two, true);
		assertEquals(againOne, false);
	}
}
