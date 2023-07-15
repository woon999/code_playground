package ch4.item18.composition;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;


class InstrumentedSetTest {

	@Test
	public void addCount_테스트(){
		InstrumentedSet<String> times = new InstrumentedSet<>(new TreeSet<>());
		times.addAll(List.of("가","나","다"));
		assertEquals(times.getAddCount(), 3);
	}

	@Test
	public void properties_테스트(){
		Properties p = new Properties();
		p.put(1,"라라");
		// System.out.println(p.getProperty(1)); // compile error
		assertEquals(p.get(1), "라라");
	}

	static class Dog{
		String name;
	}

	static void walk(Set<Dog> dogs){
		InstrumentedSet<String> iDogs = new InstrumentedSet<>(new TreeSet<>());
		// ... 이 메서드에는 dogs 대신 iDogs를 사용한다.
	}



}