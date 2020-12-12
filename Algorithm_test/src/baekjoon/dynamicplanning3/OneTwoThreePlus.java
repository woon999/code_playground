package baekjoon.dynamicplanning3;

// #15988
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OneTwoThreePlus {

	static int size = 1_000_000;
	static long mod = 1_000_000_009L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long[] dp = new long[size + 1];

		dp[0] = dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;

		for (int i = 4; i < size + 1; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % mod;
		}

		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			System.out.println(dp[num]);
		}
	}
}
