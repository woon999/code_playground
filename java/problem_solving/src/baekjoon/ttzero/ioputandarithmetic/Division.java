package baekjoon.ttzero.ioputandarithmetic;

import java.util.Scanner;

public class Division {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int a = s.nextInt(), b = s.nextInt(), c = s.nextInt();

		s.close();
		System.out.println((a + b) % c);
		System.out.println(((a % c) + (b % c)) % c);
		System.out.println((a * b) % c);
		System.out.println(((a % c) * (b % c)) % c);

	}
}
