package ch6.ingredient;

public class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;
	private final Currency currency;

	public Transaction(Trader trader, int year, int amount, Currency currency) {
		this.trader = trader;
		this.year = year;
		this.value = amount;
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

	@Override
	public String toString() {
		return "{trader=" + trader +
			", year=" + year +
			", amount=" + value + ", curreny=" + currency+"}";
	}
}