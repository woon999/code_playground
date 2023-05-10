package baekjoon.tttwo.dp;

// #15991 dp 1,2,3더하기6
import java.io.*;

public class OneTwoThreePlusSix_15991 {

	static final int MOD = (int)1e9+9;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// 1, 1
		// 2, 1+1, 2
		// 3, 1+1+1, 3
		// 4, 1+1+1+1, 1+2+1, 2+2
		// 5, 1+1+1+1+1, 1+3+1, 2+1+2
		// 6, 1+1+1+1+1+1, 1+1+2+1+1, 1+2+2+1, 2+1+1+2, 2+2+2, 3+3
		
		long[] dp = new long[100_001];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 2;
		dp[4] = 3;
		dp[5] = 3;
		dp[6] = 6;
		for(int i=7; i<100_001; i++) {
			dp[i] = (dp[i-2] + dp[i-4] + dp[i-6])%MOD;
		}
		
		for(int i=0; i<n; i++) {
			System.out.println(dp[Integer.parseInt(br.readLine())]);
		}
	}
}

