package mutualexclusionandmemorymodel;

/**
 * 멀티 스레드
 *
 * yield(): 현재 실행 중인 스레드가 사용 중인 프로세서를 양보할 용의가 있음을 스케줄러에 알려주는 힌트
 */
public class HelloWorld {
	public static void main(String[] args) throws InterruptedException{
		Thread myThread = new Thread() {
			@Override
			public void run() {
				System.out.println("Hello from new thread");
			}
		};

		myThread.start();
		Thread.yield();
		System.out.println("Hello from main thread");
		myThread.join();
	}


}
