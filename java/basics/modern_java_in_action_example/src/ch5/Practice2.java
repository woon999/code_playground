package ch5;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practice2 {
	public static void main(String[] args) {
		// 피타고라스 수 만들기
		Stream<double[]> pythagoreanTriples = IntStream.range(1, 100).boxed()
			.flatMap(
				a -> IntStream.range(a, 100)
					.mapToObj(b -> new double[] {a, b, Math.sqrt(a * a + b * b)})
					.filter(t -> t[2] % 1 == 0)
			);
		pythagoreanTriples
			.forEach(i -> System.out.println((int)i[0]+", "+(int)i[1]+", "+(int)i[2]));

		// 피보나치 수열 집합 iterate
		Stream.iterate(new int[]{0, 1},
			t -> new int[]{t[1], t[0]+t[1]})
			.limit(20) // bound
			.forEach(t -> System.out.println("("+t[0]+","+t[1]+")"));

		// generate
		Random random = new Random();
		List<Integer> OTPGenerator = Stream.generate(() -> random.nextInt(10))
			.distinct()
			.limit(5)
			.collect(toList());
		OTPGenerator.stream().forEach(i -> System.out.print(i+" "));


		/** 피보나치 수열 집합 generate
		 *
		    fib는 두 인스턴스 변수에 어떤 피보나치 변수가 들어있는지 추적하므로 가변상태이다.
		    스트림은 병렬로 처리하면서 올바른 결과를 얻으러면 불변 상태를 유지해야 한다.
		 **/
		IntSupplier fib = new IntSupplier() {
			private int prev = 0;
			private int cur = 1;

			@Override
			public int getAsInt() {
				int old = prev;
				int nxt = prev + cur;
				prev = cur;
				cur = nxt;
				return old;
			}
		};
		IntStream.generate(fib).limit(10).forEach(System.out::println);
	}
}
