package baekjoon.ttzero.whilemoon;

import java.util.Scanner;

public class AplusBFive {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		while (true) {
			int a = s.nextInt(), b = s.nextInt();
			if (a == 0 && b == 0)
				break;
			System.out.println(a + b);

		}
	}
}
