package baekjoon.ttzero.dynamicplanning3;

import java.io.*;
import java.util.StringTokenizer;

// #2163
public class CutChocolate {

	static int[][] dp;
	static int size =301;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		dp = new int[size][size];
		
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				dp[i][j] = 0;
			}
		}
		
		for(int i=2; i<size; i++) {
			dp[1][i] = i-1;
		}
		
		// n*m -1
		System.out.println(solve(n, m));
	}
	
	static int solve(int n, int m) {
		if(n==1) return dp[n][m];
		
		return solve(n-1, m) + dp[1][m] +1;
	}
	
	
}
