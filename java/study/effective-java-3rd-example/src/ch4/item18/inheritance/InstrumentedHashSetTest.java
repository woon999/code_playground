package ch4.item18.inheritance;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class InstrumentedHashSetTest {

	@Test
	public void addCount_테스트(){
		InstrumentedHashSet<String> s = new InstrumentedHashSet();
		s.addAll(List.of("가","나","다"));
		assertNotEquals(s.getAddCount(), 3);
		assertEquals(s.getAddCount(), 6);
	}

	@Test
	public void addCount_테스트2(){
		InstrumentedHashSet<String> s = new InstrumentedHashSet();
		s.dependentAddAll(List.of("가","나","다"));
		assertEquals(s.getAddCount(), 3);
	}
}