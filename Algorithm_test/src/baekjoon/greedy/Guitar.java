package baekjoon.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Guitar {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int m = s.nextInt();

		int min = 0;
		int[] pack = new int[m];
		int[] oneprice = new int[m];
		for (int i = 0; i < m; i++) {
			pack[i] = s.nextInt();
			oneprice[i] = s.nextInt();
		}
		Arrays.sort(pack);
		Arrays.sort(oneprice);

		min = Math.min((((n / 6) + 1) * pack[0]), ((n / 6) * pack[0] + (n % 6) * oneprice[0]));
		min = Math.min(min, n * oneprice[0]);
//		if (n <= 6) {
//			if (pack[0] < 6 * oneprice[0]) {
//				min = pack[0];
//			} else
//				min = n * oneprice[0];
//		}
//
//		else if (n > 6) {
//			// ¸ò * ÆÐÅ°Áö + ³ª¸ÓÁö*onep Or (¸ò+1)*ÆÐÅ°Áö Or N*³¹°³
//			min = Math.min((((n / 6) + 1) * pack[0]), ((n / 6) * pack[0] + (n % 6) * oneprice[0]));
//			min = Math.min(min, n * oneprice[0]);
//		}
		System.out.println(min);
	}

}
