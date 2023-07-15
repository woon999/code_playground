package ch2.item8.finalize_attack;

import static ch2.item8.finalize_attack.AttackVulnerable.*;

public class Main {
	public static void main(String[] args) {
		try {
			new AttackVulnerable(-1);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.gc();
		System.runFinalization();
		if (vulnerable != null) {
			System.out.println("Vulnerable object " + vulnerable + " created!");
		}
	}
}
