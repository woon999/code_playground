package hashset;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashSetTester {

	@Test
	void addTest(){
		// given, when
		HashSet<Integer> intSet = new HashSet<>();
		boolean one = intSet.add(1);
		boolean two = intSet.add(2);
		boolean againOne = intSet.add(1);

		// then
		assertEquals(one, true);
		assertEquals(two, true);
		assertEquals(againOne, false);
	}

	@Test
	void resizeTest(){
		// given. hashSet 사이즈 10으로 초기화
		int initialSize = 10;
		HashSet<Integer> intSet = new HashSet<>(initialSize);
		for(int i=0; i<7; i++){
			intSet.add(i);
		} // 70% 저장

		int tableSize = intSet.table.length;
		assertEquals(tableSize, initialSize);

		// when. 75% 초과 resizing
		intSet.add(8);

		// then
		tableSize = intSet.table.length;
		// table resize -> 2배로 증가
		assertEquals(tableSize, initialSize*2);
	}

	@Test
	void removeTest(){
		HashSet<Integer> intSet = new HashSet<>();
		// given
		boolean one = intSet.add(1);
		boolean two = intSet.add(2);

		assertEquals(one, true);
		assertEquals(two, true);
		// assertEquals(intSet.contains(1), true);

		// when
		boolean removeOne = intSet.remove(1);

		// then
		assertEquals(removeOne, true);
		// assertEquals(intSet.contains(1), false);
	}
}
