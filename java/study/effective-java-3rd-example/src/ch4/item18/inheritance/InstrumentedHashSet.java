package ch4.item18.inheritance;

import java.util.Collection;
import java.util.HashSet;

public class InstrumentedHashSet<E> extends HashSet<E> {
	// 추가된 원소의 수
	private int addCount = 0;

	public InstrumentedHashSet() {
	}

	public InstrumentedHashSet(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}

	@Override
	public boolean add(E e) {
		addCount++;
		return super.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		addCount += c.size();
		return super.addAll(c);
	}

	// 직접 addAll 구현
	public boolean dependentAddAll(Collection<? extends E> c) {
		addCount += c.size();

		boolean flag = true;
		for(E e : c){
			flag &= super.add(e);
		}
		return flag;
	}

	public int getAddCount(){
		return addCount;
	}
}
