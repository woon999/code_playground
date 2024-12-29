package baekjoon.ttzero.function;

import java.util.Arrays;

public class SelfNumber {
	private static int max = 10001;

	public static void main(String[] args) {
		boolean[] notselfnum = new boolean[max];
		Arrays.fill(notselfnum, false);

		for (int i = 0; i < max; i++) {
			int dnum = dn(i);
			if (dnum < max) {
				notselfnum[dnum] = true;
			}
		}

		for (int i = 0; i < max; i++) {
			if (notselfnum[i] == false) {
				System.out.println(i);
			}
		}

	}

	public static int dn(int n) {
		int dn = n;
		while (n > 0) {
			dn += n % 10;
			n /= 10;
		}
		return dn;
	}
}
