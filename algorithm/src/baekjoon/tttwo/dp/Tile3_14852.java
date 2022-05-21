package baekjoon.tttwo.dp;

// #14852 dp 타일 채우기 3

/**
 * n-1: 2개
 * n-2: 3개
 * 
 * 여기서 끝이 아니라, n-3부터 1까지 가로 방향으로 부정교합 조합이 2개씩 있음 (상하반전) 
 */
import java.io.*;

public class Tile3_14852 {

	static final int MOD = (int)1e9+7;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
	
		long[][] dp = new long[2][1_000_001];
		dp[0][1] = 2;
		dp[0][2] = 7;
		dp[1][2] = 1;
		for(int i=3; i<=n; i++) {
			dp[1][i] = (dp[0][i-3] + dp[1][i-1])%MOD; // (dp[0][i-3] + dp[0][i-4] + ··· + dp[0][1])
			dp[0][i] = (dp[0][i-1]*2 + dp[0][i-2]*3 + dp[1][i]*2)% MOD;
			
			
		}
		System.out.println(dp[0][n]%MOD);
	}
}