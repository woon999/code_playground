package baekjoon.ttzero.dynamicplanning2;

// #7579

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class App {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] appMem = new int[n];
		int[] appCost = new int[n];

		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			appMem[i] = Integer.parseInt(st1.nextToken());
			appCost[i] = Integer.parseInt(st2.nextToken());
		}
		
		int allCost = 0;
		for(int i : appCost) {
			allCost += i;
		}
		
		int[][] dp = new int[n][allCost+1];

		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int mem = appMem[i];
			int cost = appCost[i];

			for (int j = 0; j <= allCost; j++) {
				if (i == 0) {
					if (cost <= j) {
						dp[i][j] = mem;
					}
				} else {
					if (cost <= j) {
						dp[i][j] = Math.max(dp[i - 1][j - cost] + mem, dp[i - 1][j]);
					} else {
						dp[i][j] = dp[i - 1][j];
					}

				}
				if (dp[i][j] >= m) {
					result = Math.min(result, j);
				}

				
			}
		}
//		System.out.println(Arrays.deepToString(dp));
		System.out.println(result);
	}
}
