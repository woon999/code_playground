package functor.optional;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

// Higher-Order Functor
// f: (T,U) -> V로 매핑하는 함수 f를 (F<T>, F<U>) -> F<F<V>> 로 끌어올리는 함수
public class HighDimensionalFactor {

	// lift_e1: (T,U) -> V로 매핑하는 함수 f를 (F<T>, U) -> F<V> 로 끌어올리는 함수
	// (T,U) -> V ----> (Optional<T>, U) -> Optional<V>
	public static <T, U, V> BiFunction<Optional<T>, U, Optional<V>> lift_e1(BiFunction<T, U, V> function) {
		return (optT, u) -> optT.map(t -> function.apply(t, u));
	}

	// lift_e2: (T,U) -> V로 매핑하는 함수 f를 (T, F<U>) -> F<V> 로 끌어올리는 함수
	// (T,U) -> V ----> (T, Optional<U>) -> Optional<V>
	public static <T, U, V> BiFunction<T, Optional<U>, Optional<V>> lift_e2(BiFunction<T, U, V> function) {
		return (t, optU) -> optU.map(u -> function.apply(t, u));
	}

	// lift_2d: using lift_e1 and lift_e2
	// (T,U) -> V
	// 		life_e2
	// 		(T, F<U>) -> F<V> ->
	//						lift_e1
	// 						(F<T>, F<U>) -> F<F<V>>
	// (T,U) -> V로 매핑하는 함수 f를 (F<T>, F<U>) -> F<F<V>> 로 끌어올리는 함수

	// lift: (T -> U)를 (Optional<T> -> Optional<U>)로 변환하는 함수
	public static <T, U> Function<Optional<T>, Optional<U>> lift(Function<T, U> function) {
		return optT -> optT.map(function);
	}

	// lift_2d를 lift로만 구현
	// (T, U) -> V를 (Optional<T>, Optional<U>) -> Optional<Optional<V>>로 변환하는 lift_2d 함수
	public static <T, U, V> BiFunction<Optional<T>, Optional<U>, Optional<Optional<V>>> lift_2d(BiFunction<T, U, V> function) {
		// return (optT, optU) -> {
		// 	Function<U, V> applyWithFixedT = u -> function.apply(optT.orElse(null), u);
		// 	Function<Optional<U>, Optional<V>> liftedWithT = lift(applyWithFixedT);
		// 	return Optional.of(liftedWithT.apply(optU));
		// };
		return (optT, optU) ->
			optT.flatMap(t -> // t에 대한 차원 확장
				optU.map(u -> // u에 대한 차원 확장
					Optional.of(function.apply(t, u))
				)
			);
	}

	public static void main(String[] args) {
		// lift_e1 -------------------------------------
		// (Integer, Integer) -> Integer 형태의 함수 예제: 두 정수의 합을 구하는 함수
		BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
		// lift_e1을 사용하여 Optional<Integer>와 Integer를 받아 Optional<Integer>를 반환하는 함수로 변환
		BiFunction<Optional<Integer>, Integer, Optional<Integer>> liftedAdd = lift_e1(add);

		// 테스트
		Optional<Integer> a = Optional.of(10);
		int b = 5;
		Optional<Integer> result = liftedAdd.apply(a, b); // Optional의 값이 10이므로, 결과는 Optional.of(15)가 됩니다.
		result.ifPresent(System.out::println); // 15

		// lift_e2 -------------------------------------
		// (Integer, Integer) -> Integer 형태의 함수 예제: 두 정수의 곱을 구하는 함수
		BiFunction<Integer, Integer, Integer> multiply = (c, d) -> c * d;
		// lift_e2을 사용하여 Integer와 Optional<Integer>를 받아 Optional<Integer>를 반환하는 함수로 변환
		BiFunction<Integer, Optional<Integer>, Optional<Integer>> liftedMultiply = lift_e2(multiply);

		// 테스트
		Optional<Integer> c = Optional.of(10);
		int d = 5;
		Optional<Integer> result2 = liftedMultiply.apply(d, c); // Optional의 값이 10이므로, 결과는 Optional.of(50)가 됩니다.
		result2.ifPresent(System.out::println); // 50

		// lift_2d -------------------------------------
		// (Integer, Integer) -> Integer 형태의 함수 예제: 두 정수의 차를 구하는 함수
		BiFunction<Integer, Integer, Integer> subtract = (e, f) -> e - f;
		// lift_2d을 사용하여 Optional<Integer>와 Optional<Integer>를 받아 Optional<Optional<Integer>>를 반환하는 함수로 변환
		BiFunction<Optional<Integer>, Optional<Integer>, Optional<Optional<Integer>>> liftedSubtract = lift_2d(subtract);

		// 테스트
		Optional<Integer> e = Optional.of(10);
		Optional<Integer> f = Optional.of(5);
		Optional<Optional<Integer>> result3 = liftedSubtract.apply(e, f); // Optional의 값이 10, 5이므로, 결과는 Optional.of(Optional.of(5))가 됩니다.
		result3.ifPresent(System.out::println); // Optional[5]

	}
}
