package singleton;

public class Coin {

	private static final int ADD_MORE_COIN = 10;
	private int coin;
	private static Coin instance;

	private Coin(){
		// private 생성자로 선언하여 상속을 방지한다.
	}

	// 정적 블럭 게으른 초기화
	public static Coin getInstance(){
		try{
			Thread.sleep(1000);
		}catch (InterruptedException ex){}

		// Double check locking
		if(instance == null) {
			// 쓰레드들 대기 공간 { }
			synchronized (Coin.class) {
				if (instance == null) { // 가장 먼저 점유한 하나의 쓰레드만 해당 if문 실행
					instance = new Coin();
				}
			}
		}
		return instance;
	}

	public int getCoin(){
		return coin;
	}

	public void addMoreCoin(){
		coin += ADD_MORE_COIN;
	}

	// insertCoin이 현재 코인보다 적거나 같은 경우에만 동작한다.
	public void deductCoin(int insertCoin){
		if(coin >= insertCoin){
			coin -= insertCoin;
		}
	}
}

