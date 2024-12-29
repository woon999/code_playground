package ch7.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
	private final long[] numbers;
	private final int start;
	private final int end;
	private static final long THRESHOLD = 10_000; // 분할 최소 단위

	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}
	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}

	@Override protected Long compute() {
		int length = end - start;
		if(length <= THRESHOLD){
			return computeSequentially();
		}

		ForkJoinSumCalculator lefTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
		lefTask.fork();
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = lefTask.join();
		return leftResult + rightResult;
	}

	private long computeSequentially() {
		long sum = 0;
		for(int i=start; i<end; i++){
			sum += numbers[i];
		}
		return sum;
	}

	public static long forkJoinSum(long n){
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}
}
