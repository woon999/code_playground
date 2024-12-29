package ch2.item8.finalize_attack.security_attack;

public class AttackInsecure extends Insecure {
	static Insecure insecure;

	public AttackInsecure(int value) {
		super(value);
	}

	public void finalize() {
		insecure = this;
	}

}
