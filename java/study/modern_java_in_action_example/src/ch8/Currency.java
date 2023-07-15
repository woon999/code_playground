package ch8;

public enum Currency {
	CURRENCY_DOL("dollar"),
	CURRENCY_WON("won"),
	CURRENCY_EURO("euro");

	private String name;

	private Currency(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
