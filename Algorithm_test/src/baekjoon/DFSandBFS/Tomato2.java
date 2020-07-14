package baekjoon.DFSandBFS;

// #7569
import java.io.*;
import java.util.*;

public class Tomato2 {

	static int[][][] box;
	static int n, m, h;
	static Queue<Pos> q = new LinkedList<Pos>();
	static boolean[][][] checked;
	static int[] dx = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };
	static int lx, ly, lz;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		box = new int[n][m][h];
		checked = new boolean[n][m][h];

		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					box[i][j][k] = Integer.parseInt(st.nextToken());
					if (box[i][j][k] == 1) {
						q.add(new Pos(i, j, k));
					}
				}
			}
		}

		while (!q.isEmpty()) {
			Pos p = q.poll();
			bfs(p.x, p.y, p.z);
		}

		

//		for (int k = 0; k < h; k++) {
//			for (int i = 0; i < n; i++) {
//				for (int j = 0; j < m; j++) {
//					System.out.print(box[i][j][k] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//		}

		
		if (check(box)) {
			System.out.println(box[lx][ly][lz] - 1);
		} else {
			System.out.println(-1);
		}
	}

	static void bfs(int x, int y, int z) {
		checked[x][y][z] = true;
		q.add(new Pos(x, y, z));

		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int i = 0; i < 6; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				int nz = p.z + dz[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m && nz >= 0 && nz < h) {
					if (box[nx][ny][nz] == 0 && !checked[nx][ny][nz]) {
						box[nx][ny][nz] = box[p.x][p.y][p.z] + 1;
						q.add(new Pos(nx, ny, nz));
						lx = nx;
						ly = ny;
						lz = nz;
					}

				}
			}

		}

	}
	
	static boolean check(int[][][] arr) {
		boolean full = true;
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j =0; j< m; j++) {
					if (box[i][j][k] == 0) {
						full = false;
					}
				}
			}
		}
		return full;
	}

	static class Pos {
		int x;
		int y;
		int z;

		Pos(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
