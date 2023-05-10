package hashset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class HashSetTester {

	@Test
	void addTest_Integer(){
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
	void addTest_String(){
		// given, when
		HashSet<String> strSet = new HashSet<>();
		boolean one = strSet.add("one");
		boolean two = strSet.add("two");
		boolean againOne = strSet.add("one");

		// then
		assertEquals(one, true);
		assertEquals(two, true);
		assertEquals(againOne, false);
	}

	@Test
	void addTest_Object(){
		// given, when
		HashSet<Car> objSet = new HashSet<>();
		boolean red = objSet.add(new Car(1, "red"));
		boolean blue = objSet.add(new Car(2, "blue"));
		boolean againRed = objSet.add(new Car(1, "red"));

		// then
		assertEquals(red, true);
		assertEquals(blue, true);
		assertEquals(againRed, false);
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
	void removeTest_Integer(){
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

	@Test
	void removeTest_String(){
		HashSet<String> strSet = new HashSet<>();
		// given
		boolean one = strSet.add("one");
		boolean two = strSet.add("two");

		assertEquals(one, true);
		assertEquals(two, true);
		// assertEquals(intSet.contains(1), true);

		// when
		boolean removeOne = strSet.remove("one");

		// then
		assertEquals(removeOne, true);
		// assertEquals(intSet.contains(1), false);
	}

	@Test
	void removeTest_Object(){
		HashSet<Car> objSet = new HashSet<>();
		// given
		boolean red = objSet.add(new Car(1, "red"));
		boolean blue = objSet.add(new Car(2, "blue"));

		assertEquals(red, true);
		assertEquals(blue, true);
		// assertEquals(intSet.contains(1), true);

		// when
		boolean removeOne = objSet.remove(new Car(1, "red"));

		// then
		assertEquals(removeOne, true);
		// assertEquals(intSet.contains(1), false);
	}

	@Test
	void containsTest_Integer(){
		HashSet<Integer> intSet = new HashSet<>();
		intSet.add(1);
		intSet.add(2);

		assertTrue(intSet.contains(1));
		assertTrue(intSet.contains(2));
		assertFalse(intSet.contains(3));
	}

	@Test
	void containsTest_String(){
		HashSet<String> strSet = new HashSet<>();
		strSet.add("one");
		strSet.add("two");

		assertTrue(strSet.contains("one"));
		assertTrue(strSet.contains("two"));
		assertFalse(strSet.contains("three"));
	}

	@Test
	void containsTest_Object(){
		HashSet<Object> objSet = new HashSet<>();
		objSet.add(new Car(1, "red"));
		objSet.add(new Car(2, "blue"));

		assertTrue(objSet.contains(new Car(1, "red")));
		assertTrue(objSet.contains(new Car(2, "blue")));
		assertFalse(objSet.contains(new Car(3, "green")));
	}

}

class Car {
	int number;
	String color;

	public Car(int number, String color) {
		this.number = number;
		this.color = color;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car)o;
		return number == car.number &&
			Objects.equals(color, car.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, color);
	}
}
