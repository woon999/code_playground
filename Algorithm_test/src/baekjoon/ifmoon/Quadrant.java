package baekjoon.ifmoon;

import java.util.Scanner;

public class Quadrant {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int x = s.nextInt(), y = s.nextInt();
		s.close();
		
		if (x > 0 && y > 0) {
			System.out.println("1");
		} else if (x < 0 && y > 0) {
			System.out.println("2");
		} else if (x < 0 && y < 0) {
			System.out.println("3");
		} else if (x > 0 && y < 0) {
			System.out.println("4");
		}
	}
}
