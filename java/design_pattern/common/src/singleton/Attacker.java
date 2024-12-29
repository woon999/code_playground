package singleton;

public class Attacker implements Runnable{

	@Override
	public void run(){
		// Coin coin = Coin.getInstance();
		EnumCoin coin = EnumCoin.ENUM_COIN;

		System.out.println("[" + Thread.currentThread().getName() + "] get Coin Instance : " + coin);
		// System.out.println("time = " + (long)System.currentTimeMillis());
	}

	/**
	 * 실행하면 Coin 싱글턴 인스턴스가 여러개 생성된다.
	 * 이는 여러 개의 쓰레드가 Coin 싱글턴 인스턴스 생성 메서드에 즉, 하나의 자원에 접근하기 때문이다.
	 * 이를 해결하기 위해서는 싱글턴 인스턴스에 동기화를 걸어줘야 한다.
	 */
	public static void main(String[] args) {
		Runnable r = new Attacker();

		// System.out.println("start = " + (long)System.currentTimeMillis());
		int t = 10;
		while(t-- > 0){
			new Thread(r).start();
		}

		/**
		 * 동기화 범위에 따른 실행시간 측정 결과
		 *   1. Lazy Initialization with synchronized: 10049ms
		 *   2. Lazy Initialization, Double Checking Locking: 1041ms
		 *   3. Enum Class: 40ms
		 */

	}
}
