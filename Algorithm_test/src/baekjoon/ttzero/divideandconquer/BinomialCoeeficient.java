package baekjoon.ttzero.divideandconquer;

// #11401 

import java.io.*;
import java.util.StringTokenizer;

public class BinomialCoeeficient {

	static int MOD = 1000000007;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Integer.parseInt(st.nextToken());
		long k = Integer.parseInt(st.nextToken());
		
		long a =fac(n);
		long b = fac(n-k) *fac(k) %MOD;
		long result = a* pow(b,MOD-2)% MOD;
		
		System.out.println(result);
		
	}
	
	static long fac(long n) {
		long num = 1;
		while(n>1) {
			num = (num *n)%MOD;
			n--;
		}
		return num;
	}
	
	static long pow(long a, int b) {
		long num =1;
		long tmp =a ;
		
		while(b>0) {
			if(b%2==1) {
				num = num * tmp %MOD;
			}
			b /=2;
			tmp = (tmp*tmp)%MOD;
		}
		return num;
	}
}
