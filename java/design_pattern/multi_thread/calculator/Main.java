package calculator;

public class Main {
	public static void main(String[] args) {
		// single_thread();
		multi_thread();
	}

	private static void multi_thread() {
		try {
			long start = System.currentTimeMillis();
			MyThread.sum = 0;
			int threadCount = 32;
			int target = 2_000_000_000;
			MyThread[] threads = new MyThread[threadCount];
			for(int i = 0; i < threads.length; i++) {
				threads[i] = new MyThread(i * (target / threadCount) + 1, (i + 1) * (target / threadCount));
				threads[i].start();
			}

			for(int i = 0; i < threads.length; i++) {
				threads[i].join();
			}

			System.out.println("sum = " + MyThread.sum);
			long end = System.currentTimeMillis();
			System.out.println("time = " + (end - start) + "ms");
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void single_thread() {
		long start = System.currentTimeMillis();
		long sum = 0;
		for(int i = 1; i <= 2_000_000_000; i++) {
			sum += i;
		}
		System.out.println("sum = " + sum);
		long end = System.currentTimeMillis();
		System.out.println("time = " + (end - start) + "ms");
	}
}
