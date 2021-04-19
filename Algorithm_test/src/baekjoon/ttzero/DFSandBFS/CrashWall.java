package baekjoon.ttzero.DFSandBFS;

// #2206
import java.io.*;
import java.util.*;

public class CrashWall {


	static int n, m;
	static int[][] map;
	static int[][] checked;
	static int result;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		checked = new int[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
				checked[i][j] = Integer.MAX_VALUE;
			}
		}

		bfs(0, 0);

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				System.out.print(checked[i][j] + " ");
//			}
//			System.out.println();
//		}

		if (result == 0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

	static void bfs(int x, int y) {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(x, y, 1, 0));
		checked[x][y] = 0;

		while (!q.isEmpty()) {
			Pos p = q.poll();
			if (p.x == n - 1 && p.y == m - 1) {
				result = p.dis;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {

					if (checked[nx][ny] > p.chance) {
						if (map[nx][ny] == 0) {
							checked[nx][ny] = p.chance;
							q.add(new Pos(nx, ny, p.dis + 1, p.chance));
						} else {
							if (p.chance == 0) {
								checked[nx][ny] = p.chance + 1;
								q.add(new Pos(nx, ny, p.dis + 1, p.chance + 1));
							}
						}
					}

				}
			}

		}
	}

	static class Pos {
		int x;
		int y;
		int dis;
		int chance;

		Pos(int x, int y, int dis, int c) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.chance = c;
		}
	}
}


//6 4
//0000
//0010
//1100
//1011
//0011
//0000
