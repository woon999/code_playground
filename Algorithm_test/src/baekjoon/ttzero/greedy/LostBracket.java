package baekjoon.ttzero.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class LostBracket {
	private static String[] minus;
	private static String[] sum;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		String input = s.next();
		s.close();
		minus = input.split("-");
		System.out.println(Arrays.toString(minus));
		int result = 0;

		for (int i = 0; i < minus.length; i++) {
			sum = minus[i].split("\\+");
			int arraySum = 0;
			System.out.println(Arrays.toString(sum));
			for (String j : sum) {
				arraySum += Integer.parseInt(j);
			}

			if (i == 0)
				arraySum *= -1;

			result -= arraySum;

		}

		System.out.println(result);

	}
}
