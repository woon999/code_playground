package ch3.item14;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Example {

	// 해시코드 값의 차를 기준으로 하는 비교자 - 추이성을 위배한다!
	static Comparator<Object> hashCodeOrder = new Comparator<>() {
		@Override
		public int compare(Object o1, Object o2) {
			return o1.hashCode() - o2.hashCode();
		}
	};

	// 정적 compare 메서드를 활용한 비교자
	static Comparator<Object> hashCodeOrder2 = new Comparator<>() {
		@Override
		public int compare(Object o1, Object o2) {
			return Integer.compare(o1.hashCode(), o2.hashCode());
		}
	};

	// 비교자 생성 메서드를 활용한 비교자
	static Comparator<Object> hashCodeOrder3 =
		Comparator.comparingInt(o -> o.hashCode());

	public static void main(String[] args) {
		String[] data = {"d", "a", "b", "c"};
		Set<String> s = new TreeSet<>();
		Collections.addAll(s, data);
		System.out.println(s);

		System.out.println(hashCodeOrder.compare(new int[]{100}, new int[]{10}));
		System.out.println(hashCodeOrder2.compare("a","b"));
		System.out.println(hashCodeOrder3.compare("a","b"));
	}

	public interface Comparable<T>{
		int compareTo(T t);
	}

}
