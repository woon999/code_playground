package baekjoon.ttzero.dynamicplanning1;

// #2748

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci2 {

	static long[] f;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		f= new long[91];
		
		long result = fibo(n);
		System.out.println(result);
	}

	public static long fibo(int n) {

		if (n == 0)
			return 0;
		else if (n == 1)
			return 1;

		else {
			if (f[n] > 0)
				return f[n];
		}
		f[n] = fibo(n - 1) + fibo(n - 2);
		return f[n];
	}
}
