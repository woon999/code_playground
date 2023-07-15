package ch3.item10;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AutoValueMoney {
	public abstract String getCurrency();
	public abstract long getAmount();

	// public static AutoValueMoney create(String currency, long amount) {
		// return new AutoValue_AutoValueMoney(currency, amount);
	// }

	public static void main(String[] args) {
		// AutoValueMoney won = create("won", 100);
		// AutoValueMoney won2 = create("won", 100);
		// System.out.println(won.equals(won2));
	}
}
