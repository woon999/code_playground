package calculator;

public class MyThread extends Thread {
	public static long sum;
	private int start;
	private int end;

	public MyThread(int start, int end) {
		System.out.println("Thread " + start + " to " + end + " created.");
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		long temp = 0;
		for(int i = start; i <= end; i++) {
			temp += i;
		}

		sum += temp;
	}
}
