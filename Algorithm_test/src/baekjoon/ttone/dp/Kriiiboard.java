package baekjoon.ttone.dp;

// #11058 dp 크리보드 
import java.io.*;

public class Kriiiboard {
	
	static long[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new long[n+1];
		
		// bottom-up
//		for(int i=1; i<n+1; i++) {
//			dp[i] = dp[i-1]+1;
//			
//			if(i>6) {
//				for(int j=2; j<6; j++) {
//					dp[i] = Math.max(dp[i], dp[i-(j+1)]*j);
//				}
//			}
//		}
//		System.out.println(dp[n]);
		
		System.out.println(solve(n));
	}
	
	// top-down
	static long solve(int idx) {
		if(idx <= 0) return 0;
		
		if(idx ==1) return dp[idx] = idx;
		
		if(dp[idx] >0) return dp[idx];
		
		dp[idx] = solve(idx-1) +1;
		
		for(int i=2; i<6; i++) {
			dp[idx] = Math.max(dp[idx], solve(idx-(i+1))*i);
		}
		
		return dp[idx];
		
	}
	
}
