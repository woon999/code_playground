package baekjoon.tttwo.dp;

// #8394 dp 악수 
// 피보나치 수열 일의 자리만 출력하기 

import java.io.*;

public class HandShake {

	static final int MOD = 10;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[10_000_001];
		dp[0] = 1;
		dp[1] = 1;
		
		for(int i=2; i<=n; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%MOD;
		}
		
		System.out.println(dp[n]);
	}
}
