package baekjoon.ttzero.strproblem;

import java.io.*;

public class SumOfNumber {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		
		String s = br.readLine();
		int sum = 0;
		for(int i =0; i<n; i++) {
			sum +=s.charAt(i)-'0';
		}
		
		System.out.println(sum);
		
	}
}
