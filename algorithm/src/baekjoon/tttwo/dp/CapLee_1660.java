package baekjoon.tttwo.dp;

// #1660 dp 캡틴 이다솜 - 배낭 

import java.io.*;
import java.util.*;

public class CapLee_1660 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 1, 1 -> 1
		// 2, 3 -> 4
		// 3, 6 -> 10
		// 4, 10 -> 20
		// 5, 15 -> 35
		// 6, 21 -> 56
		int[] arr = new int[n];
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			sum += (i * (i + 1)) / 2;
			arr[i-1] = sum;
		}

		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int i = 1; i <= n; i++) {
			for (int num : arr) {
				if (num == i) {
					dp[i] = 1;
					break;
				}
				if (num > i)
					break;

				dp[i] = Math.min(dp[i], dp[i - num] + 1);
			}
		}

		System.out.println(dp[n]);
	}
}
