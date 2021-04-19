package baekjoon.ttzero.dynamicplanning3;

// #10826
import java.io.*;
import java.math.BigInteger;

public class Fibonacci4 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		BigInteger[] dp = new BigInteger[n + 1];

		if (n > -1) {
			dp[0] = BigInteger.ZERO;
		}
		if (n > 0) {
			dp[1] = BigInteger.ONE;
		}
		if (n > 1) {
			for (int i = 2; i < n + 1; i++) {
				dp[i] = dp[i - 2].add(dp[i - 1]);
			}

		}
		System.out.println(dp[n]);

	}
}
