package baekjoon.dynamicplanning2;

// #1520
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Downhill {

	static int n, m;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		dp = new int[n][m];

		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0, 0));
	}

	static int dfs(int x, int y) {
//		for (int j = 0; j < n; j++) {
//			for (int k = 0; k < m; k++) {
//				System.out.print(dp[j][k] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("-------------------------------------");
		if (x == n - 1 && y == m - 1)
			return 1;
		if (dp[x][y] != -1)
			return dp[x][y];
		else {
			dp[x][y] = 0;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
//				System.out.println("x :" +x + ", y:" +y +", nx :" + nx + ", ny : "+ny);
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[x][y] > map[nx][ny]) {
					dp[x][y] += dfs(nx, ny);
				}
				
			}
			return dp[x][y];
		}
	}
}
