package baekjoon.tttwo.dp;

// #14494 dp 다이나믹이 뭐에요? 
import java.io.*;
import java.util.*;

public class WhatIsDynamic_14494 {

	static final int MOD = (int)1e9+7;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n][m];
		for(int i=0; i<n; i++) {
			dp[i][0] = 1;
		}
		for(int i=0; i<m; i++) {
			dp[0][i] = 1;
		}
		
		for(int i=1; i<n; i++) {
			for(int j=1; j<m; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i-1][j-1])%MOD + dp[i][j-1];
				dp[i][j] %= MOD;
			}
		}
		System.out.println(dp[n-1][m-1]);
	}
}
