package baekjoon.tttwo.dp;

// #2410 dp 2의 멱수의 합 

import java.io.*;

public class SumOfTwoPower_2410 {

	static final int MOD = (int)1e9;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 1, 1 // 1
		// 2, 1+1, 2  // 2
		// 3, 1+1+1, 1+2 // 2
		// 4, 1+1+1+1, 1+1+2, 2*(1+1), 2*2 // dp[2] + dp[3]  =4
		// 5, 1+1+1+1+1, 1+1+1+2, 1+2+2, 1+4 // 4
		// 6, 1+1+1+1+1+1, 1+1+1+1+2, 1+1+2+2, 1+1+4, 2*(1+1+1), 2(1+2) // dp[5] + d[3] = 6
		
		int size = 1_000_001;
		int[] dp = new int[size];
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3; i<size; i++) {
			if(i%2 == 1) {
				dp[i] = dp[i-1];
			} else {
				dp[i] = (dp[i-1] + dp[i/2]) %MOD;
			}
		}
		
		System.out.println(dp[n]);
	}
}
