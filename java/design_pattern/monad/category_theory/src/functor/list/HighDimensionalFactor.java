package functor.list;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

// Higher-Order Functor
// f: (T,U) -> V로 매핑하는 함수 f를 (F<T>, F<U>) -> F<F<V>> 로 끌어올리는 함수
public class HighDimensionalFactor {

	// lift_e1: (T,U) -> V로 매핑하는 함수 f를 (F<T>, U) -> F<V> 로 끌어올리는 함수
	// (T,U) -> V ----> (List<T>, U) -> List<V>
	public static <T, U, V> BiFunction<List<T>, U, List<V>> lift_e1(BiFunction<T, U, V> function) {
		return (listT, u) -> listT.stream().map(t -> function.apply(t, u)).toList();
	}

	// lift_e2: (T,U) -> V로 매핑하는 함수 f를 (T, F<U>) -> F<V> 로 끌어올리는 함수
	// (T,U) -> V ----> (T, List<U>) -> List<V>
	public static <T, U, V> BiFunction<T, List<U>, List<V>> lift_e2(BiFunction<T, U, V> function) {
		return (t, listU) -> listU.stream().map(u -> function.apply(t, u)).toList();
	}

	// lift_2d: using lift_e1 and lift_e2
	// (T,U) -> V
	// 		life_e2
	// 		(T, F<U>) -> F<V> ->
	//						lift_e1
	// 						(F<T>, F<U>) -> F<F<V>>
	// (T,U) -> V로 매핑하는 함수 f를 (F<T>, F<U>) -> F<F<V>> 로 끌어올리는 함수

	// lift: (T -> U)를 (List<T> -> List<U>)로 변환하는 함수
	public static <T, U> Function<List<T>, List<U>> lift(Function<T, U> function) {
		return listT -> listT.stream().map(function).toList();
	}

	// lift_2d: (T,U) -> V로 매핑하는 함수 f를 (List<T>, List<U>) -> List<List<V>>로 끌어올리는 함수
	public static <T, U, V> BiFunction<List<T>, List<U>, List<List<V>>> lift_2d(BiFunction<T, U, V> function) {
		return (listT, listU) ->
			listT.stream().map(t ->
				listU.stream().map(u ->
					function.apply(t, u)
				).collect(Collectors.toList())
			).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		// lift_e1 -------------------------------------
		BiFunction<Integer, String, String> concat = (x, y) -> x + y;
		List<String> result1 = lift_e1(concat).apply(Arrays.asList(1, 2, 3), "a");
		System.out.println(result1); // [1a, 2a, 3a]

		// lift_e2 -------------------------------------
		BiFunction<String, Integer, String> concat2 = (x, y) -> x + y;
		List<String> result2 = lift_e2(concat2).apply("a", Arrays.asList(1, 2, 3));
		System.out.println(result2); // [a1, a2, a3]


		// lift_2d -------------------------------------
		BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;
		List<List<Integer>> result3 = lift_2d(add).apply(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
		System.out.println(result3); // [[5, 6, 7], [6, 7, 8], [7, 8, 9]]
	}
}
