package baekjoon.ttzero.ioputandarithmetic;

import java.util.Scanner;

public class Multiple {

	public static void main(String[] args) {	
		Scanner s = new Scanner(System.in);
	
		int n = s.nextInt(), m = s.nextInt();
		s.close();
		System.out.println(m%100%10 * n);
		System.out.println(m%100/10 * n);
		System.out.println(m/100 * n);
		System.out.println(m*n);
	}
}
