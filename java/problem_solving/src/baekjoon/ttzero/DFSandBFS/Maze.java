package baekjoon.ttzero.DFSandBFS;

import java.io.*;
import java.util.*;

public class Maze {

	static int n;
	static int m;
	static int[][] field;
	static boolean[][] checked;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		field = new int[n][m];
		checked = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				field[i][j] = s.charAt(j) - '0';
			}
		}
		bfs(0, 0);

		System.out.println(field[n-1][m-1]);
	}

	static void bfs(int x, int y) {

		Queue<Dot> q = new LinkedList<Dot>();
		q.add(new Dot(x, y));
		checked[x][y] = true;
		while (!q.isEmpty()) {
			Dot d = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = d.x + dx[i];
				int ny = d.y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (field[nx][ny] == 1 && !checked[nx][ny]) {
						q.add(new Dot(nx, ny));
						field[nx][ny] = field[d.x][d.y] + 1;
					
					}
				}
			}

		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(field[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-------------");

	}

	static class Dot {
		int x;
		int y;

		Dot(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
