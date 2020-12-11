package baekjoon.dynamicplanning3;

// #1937
import java.io.*;
import java.util.*;

public class Panda {

	static int n;
	static int max;
	static int[][] arr;
	static int[][] dp;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		dp = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(max, dfs(i, j));
			}
		}

		System.out.println(max);
	}

	private static int dfs(int x, int y) {
		if (dp[x][y] != 0)
			return dp[x][y];

		int cnt = 1;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
				if (arr[x][y] < arr[nx][ny]) {
					cnt = Math.max(dfs(nx, ny) + 1, cnt);
				}
			}
		}
		return dp[x][y] =cnt;
	}

}
