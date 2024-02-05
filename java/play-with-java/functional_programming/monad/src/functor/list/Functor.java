package functor.list;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Functor {
	// T -> U로 매핑하는 함수 f를 List<T> -> List<U>로 끌어올리는 함수
	public static <T, U> List<U> lift(List<T> list, Function<T, U> f) {
		// List<U> result = new ArrayList<>();
		// for (T t : list) {
		// 	result.add(f.apply(t));
		// }
		// return result;
		return list.stream().map(f).collect(Collectors.toList());
	}


	public static void main(String[] args) {
		// lift ----------------------------------------
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		Function<Integer, Integer> square = x -> x * x;
		List<Integer> result = lift(list, square);
		System.out.println(result); // [1, 4, 9, 16, 25]
	}
}
