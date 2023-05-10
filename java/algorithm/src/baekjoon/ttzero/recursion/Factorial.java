package baekjoon.ttzero.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(fact(n));
		
	}
	public static int fact(int n) {
		if(n==0) return 1;
		else if(n==1) return 1;
		
		return n*fact(n-1);
	}
}
