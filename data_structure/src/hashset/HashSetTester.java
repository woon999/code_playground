package hashset;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HashSetTester {



	@Test
	void addTest(){
		HashSet<Integer> intSet = new HashSet<>();
		boolean one = intSet.add(1);
		boolean two = intSet.add(2);
		boolean againOne = intSet.add(1);

		assertEquals(one, true);
		assertEquals(two, true);
		assertEquals(againOne, false);
	}

	@Test
	void resizeTest(){
		// hashSet 사이즈 10으로 초기화
		int initialSize = 10;
		HashSet<Integer> intSet = new HashSet<>(initialSize);
		for(int i=0; i<7; i++){
			intSet.add(i);
		} // 70% 저장

		int tableSize = intSet.table.length;
		assertEquals(tableSize, initialSize);

		// 75% 초과 resizing
		intSet.add(8);

		tableSize = intSet.table.length;
		// table resize -> 2배로 증가
		assertEquals(tableSize, initialSize*2);
	}
}
