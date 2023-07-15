package ch5.item31.pecs;

import java.util.HashSet;
import java.util.Set;

public class Practice {
	public static void main(String[] args) {
		Set<String> guys = Set.of("톰", "딕", "해리");
		Set<String> stooges = Set.of("래리", "모에", "컬리");
		Set<String> aflCio = union(guys, stooges);
		System.out.println(aflCio);

		Set<Integer> integers = Set.of(1,3,5);
		Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
		Set<Number> numbers = union(integers, doubles);
		System.out.println("numbers = " + numbers);
	}

	public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2){
		Set<E> result = new HashSet<>(s1);
		result.addAll(s2);
		return result;
	}

}
