package baekjoon.ttzero.pratice;

import java.util.*;

public class SecondNumber {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int[] num = new int[3];
		
		for(int i=0; i<num.length; i++) {
			num[i] = s.nextInt();
		}
		s.close();
		
		Arrays.sort(num);
		System.out.println(num[1]);
	}
}
