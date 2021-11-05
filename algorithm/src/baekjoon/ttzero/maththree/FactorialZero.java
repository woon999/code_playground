package baekjoon.ttzero.maththree;

import java.io.*;

public class FactorialZero {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int a = 0;
		
		for(int i=5; i<=n; i*=5) {
			a += n/i;
		}

		System.out.println(a);
	}
}
