package baekjoon.ttzero.DFSandBFS;

// #1012
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class OrganicCabbage {

	static int n;
	static int m;
	static int[][] field;
	static boolean[][] checked;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int t = 0; t < test; t++) {
			int count =0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			field = new int[n][m];
			checked = new boolean[n][m];
		
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				field[b][a] = 1;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (field[i][j] == 1 && !checked[i][j]) {
						count++;
						dfs(i, j);
					}
				}
			}
			sb.append(count+"\n");
		}
		
		System.out.println(sb);
	}

	static void dfs(int x, int y) {
		checked[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
				if (field[nx][ny] == 1 && !checked[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}
	}
}
