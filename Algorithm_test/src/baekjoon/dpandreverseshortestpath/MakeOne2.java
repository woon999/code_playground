package baekjoon.dpandreverseshortestpath;

// #12582
import java.io.*;
import java.util.Stack;

public class MakeOne2 {

	static int INF = 543216789;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		dp = new int[n + 1][2];

		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = INF;
		}
		dp[n][0] = 0;
		solve(n);

//		for(int i=0; i<dp.length;i++) {
//			for(int j=0; j<2; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		sb.append(dp[1][0] + "\n");

		Stack<Integer> st = new Stack<>();
		int i = 1;

			while (st.size() != dp[1][0]) {
				st.push(i = dp[i][1]);
			}

			while (!st.isEmpty()) {
				sb.append(st.pop() + " ");
			}

		sb.append(1);
		System.out.println(sb.toString());
	}

	static void solve(int n) {

		int min = dp[n][0] + 1;

		if (n == 1)
			return;

		if (n % 3 == 0) {
			dp[n / 3][0] = Math.min(dp[n / 3][0], min);

			if (dp[n / 3][0] == min) {
				dp[n / 3][1] = n;
				solve(n / 3);
			}

		}
		if (n % 2 == 0) {
			dp[n / 2][0] = Math.min(dp[n / 2][0], min);

			if (dp[n / 2][0] == min) {
				dp[n / 2][1] = n;
				solve(n / 2);
			}

		}

		dp[n - 1][0] = Math.min(dp[n - 1][0], min);

		if (dp[n - 1][0] == min) {
			dp[n - 1][1] = n;
			solve(n - 1);
		}

	}

}
