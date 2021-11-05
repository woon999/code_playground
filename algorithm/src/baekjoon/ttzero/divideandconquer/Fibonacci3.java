package baekjoon.ttzero.divideandconquer;

// #2749
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Fibonacci3 {

	static long[] fibo;
	static int div = 1000000;
	static int pisano = 1500000;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		long n = Long.parseLong(br.readLine());
		
		n = n%pisano;
		fibo = new long[pisano+1];
		fi((int)n);

		System.out.println(fibo[(int)n]);
	}
	static void fi(int n) {
		fibo[1] =1;
		
		for(int i=2; i< pisano; i++) {
			fibo[i] = (fibo[i-1] + fibo[i-2]) %div;
		}
		
	}
}
