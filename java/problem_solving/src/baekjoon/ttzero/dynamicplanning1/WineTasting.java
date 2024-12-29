package baekjoon.ttzero.dynamicplanning1;

//#2156

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



public class WineTasting {
	static int[] wine;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		wine = new int[n];
		dp = new int[n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine());
			wine[i] =  Integer.parseInt(st.nextToken());
		}
		func(n);
		System.out.println(dp[n-1]);
		
		
	}
	
	static void func(int n) {
		if(n==1) {
			dp[0] = wine[0];
		}
		else if(n==2) {
			dp[1] = wine[0] + wine[1];
		}
		else {
			dp[0] = wine[0];
			dp[1] = wine[0] + wine[1];
			dp[2] = Math.max(dp[1],  Math.max(wine[0] + wine[2], wine[1] + wine[2]));
			
			for(int i=3; i<n; i++) {
				dp[i] = Math.max(dp[i-1],Math.max(dp[i-3] + wine[i] + wine[i-1], dp[i-2]+ wine[i]));
			}
		}
		
	}

}
