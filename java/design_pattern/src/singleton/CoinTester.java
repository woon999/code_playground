package singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoinTester {

	@Test
	void coinSingletonInstance_test(){
		Coin coin1 = Coin.getInstance();
		Coin coin2 = Coin.getInstance();

		assertEquals(coin1, coin2);
		assertEquals(coin1.getCoin(), coin2.getCoin());

		// coin1 객체의 coin 수 증가
		coin1.addMoreCoin();

		assertEquals(coin1.getCoin(), coin2.getCoin());
	}

}