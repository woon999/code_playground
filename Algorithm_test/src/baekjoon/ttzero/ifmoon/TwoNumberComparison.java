package baekjoon.ttzero.ifmoon;

import java.util.Scanner;

public class TwoNumberComparison {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int a = s.nextInt(), b = s.nextInt();
		s.close();
		
		if(a>b) {
			System.out.println(">");
		}
		else if(a<b) {
			System.out.println("<");
		}else {
			System.out.println("==");
		}
	}
}
