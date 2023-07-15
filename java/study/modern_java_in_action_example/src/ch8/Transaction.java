package ch8;


public class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;
	private final String referenceCode;
	private final Currency currency;

	public Transaction(Trader trader, int year, int amount, String referenceCode, Currency currency) {
		this.trader = trader;
		this.year = year;
		this.value = amount;
		this.referenceCode = referenceCode;
		this.currency = currency;
	}

	public Trader getTrader() {
		return trader;
	}

	public int getYear() {
		return year;
	}

	public int getValue() {
		return value;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	@Override
	public String toString() {
		return "{trader=" + trader +
			", year=" + year +
			", value=" + value +
			", referenceCode=" + referenceCode +
			", currency=" + currency +
			'}';
	}
}