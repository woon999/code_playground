package ch9.logging;

import java.util.Arrays;
import java.util.List;

public class Practice {

	public static void main(String[] args) {
		List<Integer> numbers  = Arrays.asList(1,2,3,4,5);

		// numbers.stream()
		// 	.map(x -> x+17)
		// 	.filter(x -> x%2==0)
		// 	.limit(3)
		// 	.forEach(System.out::println);

		// peek() logging
		numbers.stream()
			.peek(x -> System.out.println("from stream: " + x))
			.map(x -> x+17)
			.peek(x -> System.out.println("after map: " + x))
			.filter(x -> x%2==0)
			.peek(x -> System.out.println("after filter: " + x))
			.limit(3)
			.peek(x -> System.out.println("after limit: " + x))
			.forEach(System.out::println);
	}
}
