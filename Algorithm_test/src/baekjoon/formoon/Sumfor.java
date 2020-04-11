package baekjoon.formoon;

import java.util.Scanner;

public class Sumfor {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		int sum = 0;
		for(int i = 1; i<=n; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
