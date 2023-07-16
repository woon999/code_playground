package calculator.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
	long from, to;

	SumTask(long from, long to) {
		this.from = from;
		this.to = to;
	}

	@Override
	protected Long compute() {
		long size = to - from + 1;

		if(size <= 5)
			return sum();

		long half = (from+to)/2;

		SumTask leftSum = new SumTask(from, half);
		SumTask rightSum = new SumTask(half+1, to);

		leftSum.fork(); // 작업(leftSum)을 쓰레드 풀 작업 큐에 넣는다.

		return rightSum.compute() + leftSum.join();
	}

	private long sum(){ // from - to의 모든 숫자를 더한 결과를 반환
		long tmp = 0L;
		for(long i=from; i<=to; i++){
			tmp += i;
		}
		return tmp;
	}

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		SumTask task = new SumTask(1L, 2_000_000_000L);

		long start = System.currentTimeMillis();
		Long result = pool.invoke(task);
		System.out.println("result = " + result);
		long end = System.currentTimeMillis();
		System.out.println("time = " + (end - start) + "ms");
	}

	// result = 2000000001000000000
	// time = 4874ms
}
