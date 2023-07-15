package ch4.item24.nonstaticmemeber;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Set;

// 어댑터 클래스
public class MySet<E> extends AbstractSet<E> {

	@Override public Iterator<E> iterator() {
		return new MyIterator();
	}

	@Override public int size() {
		return 0;
	}

	// 비정적 멤버 클래스
	private class MyIterator implements Iterator<E>{
		@Override public boolean hasNext() {
			return false;
		}
		@Override public E next() {
			return null;
		}
	}

	public static void main(String[] args) {
		Set<Integer> set = new MySet<>();
	}
}
