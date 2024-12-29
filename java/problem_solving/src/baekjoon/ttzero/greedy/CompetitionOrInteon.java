package baekjoon.ttzero.greedy;

import java.util.Scanner;

public class CompetitionOrInteon {

	private static int max = 0;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int n = s.nextInt(), m = s.nextInt(), k = s.nextInt();

		System.out.println(max(n,m,k));
	}

	public static int max(int n, int m, int k) {

		if (n / 2 > m) {
			max = m;
		} else {
			max = n / 2;
		}

		k -= ((n - 2 * max) + (m - max));

		while (k > 0) {
			k -= 3;
			max -= 1;
		}
		return max;
	}
}
