package baekjoon.mathone;

import java.util.Scanner;

public class SugarDeliever {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		s.close();
		if (n % 5 == 0) {
			System.out.println(n / 5);
			return;
		} else {
			int q = n / 5;
			for (int i = q; i > 0; i--) {
				int temp = n - (i * 5);
				if (temp % 3 == 0) {
					System.out.println(i + (temp / 3));
					return;
				}
			}

		}
		if (n % 3 == 0) {
			System.out.println(n / 3);
			return;
		} else {
			System.out.println(-1);
			return;
		}

	}
}
