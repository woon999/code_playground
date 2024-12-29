package baekjoon.ttzero.whilemoon;

import java.util.Scanner;

public class AplusBFour {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		
		while(s.hasNextInt()) {
			
			int a = s.nextInt(), b = s.nextInt();
			
			System.out.println(a+b);
		}

	}

}
