package baekjoon.greedy;

import java.util.Scanner;

public class GreedyStringGap {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		String x = s.next();
		String y = s.next();
		s.close();

		if (x.length() <= y.length()) {
			System.out.println(min(x, y));

		} else {
			System.out.println("b가 a보다 같거나 커야합니다.");
		}

	}

	public static int min(String x, String y) {

		int result = x.length();

		for (int i = 0; i <= y.length() - x.length(); i++) {
			int count = 0;
			for (int j = 0; j < x.length(); j++) {
				if (x.charAt(j) != y.charAt(i + j)) {
					count++;
				}
			}
			result = result > count ? count : result;

		}

		return result;
	}
}
