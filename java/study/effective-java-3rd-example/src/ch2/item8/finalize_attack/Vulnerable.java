package ch2.item8.finalize_attack;

public class Vulnerable {
	Integer value = 999;

	Vulnerable(int value) {
		if (value <= 0) {
			throw new IllegalArgumentException("Vulnerable value must be positive");
		}
		this.value = value;
	}

	@Override
	public String toString() {
		return (value.toString());
	}
}
