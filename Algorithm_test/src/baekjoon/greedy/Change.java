package baekjoon.greedy;

import java.util.Scanner;

public class Change {

	private static int[] money = { 500, 100, 50, 10, 5, 1 };

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int charge = 1000 - s.nextInt();
		System.out.println(min(charge));
		s.close();
	}

	public static int min(int c) {

		int count = 0;
		for (int i = 0; i < money.length; i++) {
			if (c / money[i] > 0) {
				count += c / money[i];
				c = c - money[i] * (c / money[i]);
			}
		}

		return count;
	}
}
