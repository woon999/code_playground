package baekjoon.ttzero.ifmoon;

import java.util.Scanner;

public class YoonYear {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int year = s.nextInt();

		s.close();
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
			System.out.println("1");

		} else
			System.out.println("0");
	}
}
