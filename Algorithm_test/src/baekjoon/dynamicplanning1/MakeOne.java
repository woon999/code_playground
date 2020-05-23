package baekjoon.dynamicplanning1;

// #1463
import java.io.*;
import java.util.Arrays;

public class MakeOne {

	static int[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
	
		memo = new int[n+1];
		System.out.println(one(n));
		System.out.println(Arrays.toString(memo));

	}

	static int one(int n) {

		if(n ==1) return 0;
		if(memo[n]>0) {
			return memo[n];
		}
		
		memo[n] = one(n-1) +1;
		if(n%3 ==0) {
			memo[n] = Math.min(memo[n], one(n/3)+1);
		}
		if(n%2==0) {
			memo[n] = Math.min(memo[n], one(n/2)+1);
		}	
			
		return memo[n];
	}
}
