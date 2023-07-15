package mutualexclusionandmemorymodel;

/**
 * 첫 번째 잠금장치 - synchronized
 */

public class Counting_FirstLock {
	public static void main(String[] args) throws InterruptedException{
		class Counter{
			private int count = 0;
			public synchronized void increment() { ++count; }
			public int getCount() { return count; }
		}

		final Counter counter = new Counter();
		class CountingThread extends Thread {
			public void run(){
				for(int x=0; x<10_000; x++){
					counter.increment();
				}
			}
		}

		CountingThread t1 = new CountingThread();
		CountingThread t2 = new CountingThread();
		t1.start(); t2.start();
		t1.join(); t2.join();
		System.out.println(counter.getCount());
	}
}
