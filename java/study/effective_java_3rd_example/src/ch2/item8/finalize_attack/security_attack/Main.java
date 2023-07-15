package ch2.item8.finalize_attack.security_attack;

import static ch2.item8.finalize_attack.security_attack.AttackInsecure.*;

public class Main {

	public static void main(String[] args) {
		try {
			new AttackInsecure(-1);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.gc();
		System.runFinalization();
		if (insecure != null) {
			System.out.println("Insecure object " + insecure + " created!");
		}
	}
}
