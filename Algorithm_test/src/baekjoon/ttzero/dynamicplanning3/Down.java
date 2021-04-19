package baekjoon.ttzero.dynamicplanning3;

// #2096
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Down {

	static int n;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		dp = new int[n + 1][3];
		int[][] max = new int[n + 1][3];
		int[][] min = new int[n + 1][3];

		for (int i = 1; i < n + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			dp[i][0] = Integer.parseInt(st.nextToken());
			dp[i][1] = Integer.parseInt(st.nextToken());
			dp[i][2] = Integer.parseInt(st.nextToken());

		}

		sb.append(solve(dp, true) + " ");
		sb.append(solve(dp, false));

		System.out.println(sb.toString());

	}

	static int solve(int[][] arr, boolean flag) {

		int res = 0;
		arr = new int[n + 1][3];

		if (flag) {
			for (int i = 1; i < n + 1; i++) {
				arr[i][0] = Math.max(arr[i - 1][0], arr[i - 1][1]);
				arr[i][0] += dp[i][0];

				arr[i][1] = Math.max(arr[i - 1][0], Math.max(arr[i - 1][1], arr[i - 1][2]));
				arr[i][1] += dp[i][1];

				arr[i][2] = Math.max(arr[i - 1][2], arr[i - 1][1]);
				arr[i][2] += dp[i][2];
			}
			return res = Math.max(arr[n][0], Math.max(arr[n][1], arr[n][2]));

		} else {
			for (int i = 1; i < n + 1; i++) {
				arr[i][0] = Math.min(arr[i - 1][0], arr[i - 1][1]);
				arr[i][0] += dp[i][0];

				arr[i][1] = Math.min(arr[i - 1][0], Math.min(arr[i - 1][1], arr[i - 1][2]));
				arr[i][1] += dp[i][1];

				arr[i][2] = Math.min(arr[i - 1][2], arr[i - 1][1]);
				arr[i][2] += dp[i][2];
			}
			return res = Math.min(arr[n][0], Math.min(arr[n][1], arr[n][2]));
		}
	}
}
