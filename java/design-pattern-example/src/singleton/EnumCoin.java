package singleton;

public enum EnumCoin {
	ENUM_COIN;

	private static final int ADD_MORE_COIN = 10;
	private int coin;

	public int getCoin() {
		return coin;
	}

	public void addMoreCoin(){
		coin += ADD_MORE_COIN;
	}
}

