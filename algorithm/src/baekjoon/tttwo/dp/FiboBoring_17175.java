package baekjoon.tttwo.dp;

// #17175 dp 피보나치는 지겨웡~  
import java.io.*;

public class FiboBoring_17175 {

	static final int MOD = (int) 1e9 + 7;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int[] dp = new int[51];
		dp[0] = 1;
		dp[1] = 1;

		for (int i = 2; i <= n; i++) {
			dp[i] = (1 + dp[i - 1] + dp[i - 2]) % MOD;
		}

		System.out.println(dp[n]);
	}
}
