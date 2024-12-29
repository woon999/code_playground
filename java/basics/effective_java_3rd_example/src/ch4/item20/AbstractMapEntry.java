package ch4.item20;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V>{

	// 변경 가능한 엔트리는 이 메서드를 반드시 재정의해야 한다.
	@Override public V setValue(V value) {
		throw new UnsupportedOperationException();
	}

	// May.Entry.hashCode 일반 규약을 구현한다.
	@Override public int hashCode() {
		return Objects.hashCode(getKey())
			^ Objects.hashCode(getValue());
	}

	// May.Entry.equals 일반 규약을 구현한다.
	@Override public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Map.Entry)) return false;
		Map.Entry<?,?> e = (Map.Entry) o;
		return Objects.equals(e.getKey(), getKey())
			&& Objects.equals(e.getKey(), getValue());
	}

	@Override public String toString() {
		return getKey()+ " = " + getValue();
	}
}
