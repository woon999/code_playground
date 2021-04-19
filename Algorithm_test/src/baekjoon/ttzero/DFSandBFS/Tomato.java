package baekjoon.ttzero.DFSandBFS;

// #7576
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Tomato {

	static int[][] box;
	static int n, m;
	static Queue<Pos> q = new LinkedList<Pos>();
	static boolean[][] checked;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int lx,ly;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
	
		
		box = new int[n][m];
		checked = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());

				if (box[i][j] == 1) {
					q.add(new Pos(i, j));
				}
			}
		}
		
		while(!q.isEmpty()) {
			Pos p = q.poll();
			bfs(p.x,p.y);
		}
		
		boolean full = true;
		
		for(int i=0; i<n; i++) {
			for(int j : box[i]) {
				if(j==0) {
					full = false;
				}
			}
		}
		if(full) {
		System.out.println(box[lx][ly]-1);
		}
		else {
			System.out.println(-1);
		}
	}

	static void bfs(int x, int y) {
		checked[x][y] = true;
		q.add(new Pos(x,y));
	
		while (!q.isEmpty()) {
			Pos p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (box[nx][ny] == 0 && !checked[nx][ny]) {
						box[nx][ny]= box[p.x][p.y]+1;
						q.add(new Pos(nx, ny));
						lx = nx; ly = ny;
					}
					
				}
			}
			
		}

	}

	static class Pos {
		int x;
		int y;

		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

