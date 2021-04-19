package baekjoon.ttone.dp;

// # 17626 dp Four Squares 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FourSquares {

	static int[] dp;
	static int min;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		int n = Integer.parseInt(br.readLine());
		 dp = new int[n + 1];
		 dp[0] = 0;
	     dp[1] = 1;
	 
	     solve(n);
	 
	     System.out.println(dp[n]);

	}
	
//	static int solve(int idx) {
//		if(dp[idx] != 0) return dp[idx];
//		
//		dp[idx] = (int)Math.sqrt(idx);
////		min = Integer.MAX_VALUE;
//		for (int i = 1; i * i <= idx; i++) {
//            dp[idx] = Math.min(dp[idx], solve(idx - i * i));
//        }
//		dp[idx]+= 1; 
//		
//		return dp[idx];
//		 
//	}

	
	static void solve(int n) {
		for (int i = 2; i <= n; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 1; j * j <= i; j++) {
				min = Math.min(min, dp[i - j * j]);
			}
			dp[i] = min + 1; 
		}
	}

}
