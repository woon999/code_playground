package ch2.item8.finalize_attack;

public class AttackVulnerable extends Vulnerable {
	static Vulnerable vulnerable;

	public AttackVulnerable(int value) {
		super(value);
	}
	public void finalize() {
		vulnerable = this;
	}
}