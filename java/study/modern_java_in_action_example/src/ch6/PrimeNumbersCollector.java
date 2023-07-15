package ch6;

import static java.util.stream.Collector.Characteristics.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer,
													Map<Boolean, List<Integer>>,
													Map<Boolean, List<Integer>>> {
	// 누적자를 만드는 함수 반환
	@Override public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<>(){{
			put(true, new ArrayList<>());
			put(false, new ArrayList<>());
		}};
	}

	// 최적화의 핵심
	@Override public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> {
			acc.get(isPrime(acc.get(true), candidate)).add(candidate);
		};
	}

	// 그냥 학습 목적으로 구현
	@Override public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}


	@Override public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

	public static boolean isPrime(List<Integer> primes, int candidate){
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return primes.stream()
			.takeWhile(i -> i<=candidateRoot)
			.noneMatch(i -> candidate % i ==0);
	}
}
