package baekjoon.ttzero.DFSandBFS;

// #2667
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class ComplexNumber {

	static int n;
	static int[][] house;
	static int count;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static boolean[][] checked;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());

		house = new int[n + 1][n + 1];
		checked = new boolean[n + 1][n + 1];

		for (int i = 1; i < n + 1; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				house[i][j + 1] = s.charAt(j) - '0';
			}
		}

		int total = 0;
		Queue<Integer> q = new PriorityQueue<Integer>();

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (house[i][j] == 1  && !checked[i][j]) {
					count = 0;

					total++;
					dfs(i, j);

					q.add(count);
					
				}

			}
		}

		sb.append(total + "\n");

		while (!q.isEmpty()) {
			sb.append(q.poll() + "\n");
		}

		System.out.println(sb);
	}

	static void dfs(int x, int y) {
		checked[x][y] = true;
		count++;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
				if (house[nx][ny] == 1 && !checked[nx][ny]) {
					dfs(nx, ny);
				}
			}
		}

	}
}
