package functor.optional;

import java.util.Optional;
import java.util.function.Function;

// Functor
// f: T -> U로 매핑하는 함수 f를 F<T> -> F<U>로 끌어올리는 함수 (수학으로 치면 적분, 차원 확장)
// F<T>는 컨테이너 타입이며, Optional<T>, List<T>, Stream<T> 등이 해당된다.
public class Functor {
	public static <T, U> Function<Optional<T>, Optional<U>> lift(Function<T, U> f) {
		return optT -> {
			if (!optT.isPresent()) {
				return Optional.empty();
			} else {
				return Optional.ofNullable(f.apply(optT.get()));
			}
		};
	}

	// T -> U로 매핑하는 함수 f를 Optional<T> -> Optional<U>로 끌어올리는 함수
	public static void main(String[] args) {
		Function<Integer, Double> squareRoot = Math::sqrt; // f
		Function<Optional<Integer>, Optional<Double>> liftedSquareRoot = lift(squareRoot);

		Optional<Integer> value = Optional.of(4);
		Optional<Double> result = liftedSquareRoot.apply(value);

		result.ifPresent(System.out::println); // 결과 출력: 2.0
	}
}
