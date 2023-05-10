package baekjoon.ttone.dp;

// #9658 dp 돌 게임4 
import java.io.*;
import java.util.Arrays;

public class StoneGame4 {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		dp = new int[1001];
		
		
		// bottom-up
//		dp[1] =1;
//		dp[2] =0;
//		dp[3] =1;
//		
//		for(int i=4; i<=n; i++) {
//			if(dp[i-1] + dp[i-3] + dp[i-4]>0) {
//				dp[i] =0;
//			}else {
//				dp[i] =1;
//			}
//			
//		}
//		if(dp[n] ==0) {
//			System.out.println("SK");
//		}else {
//			System.out.println("CY");
//		}
		
		// top-down
		Arrays.fill(dp, -1);
		
		if(solve(n) ==0) {;
			System.out.println("SK");
		}else {
			System.out.println("CY");
		}
		
	}
	
	//top-down
	static int solve(int num) {
		if(num <= 0) return 0;
		if(dp[num]!=-1) return dp[num];
		
		int win = solve(num-1) + solve(num-3) + solve(num-4);
		if(win >0	) {
			return dp[num] = 0;
		}else {
			return dp[num] = 1;
		}
		
		 
	}
}
