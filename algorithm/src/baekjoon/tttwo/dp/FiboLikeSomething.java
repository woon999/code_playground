package baekjoon.tttwo.dp;

// #14495 dp 피보나치 비스무레한 수열 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiboLikeSomething {

	static long[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		dp = new long[117];
		dp[1] = dp[2] = dp[3] = 1;
		for(int i=4; i<=n; i++) {
			dp[i] = dp[i-1] + dp[i-3];
		}
		System.out.println(dp[n]);
	}
}
