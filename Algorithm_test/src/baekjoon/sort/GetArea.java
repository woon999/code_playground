package baekjoon.sort;

// #2583
import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class GetArea {

	static int n, m, k;
	static int[][] area;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int res = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		area = new int[m][n];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			makeSquare(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		}


		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		int count = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				res = 0;
				if(area[i][j]==0) {
					count ++;
					dfs(i,j);
					pq.offer(res);
				}

			}
		}
		
		System.out.println(count);
		while(!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}

	}
	
	static void dfs(int x, int y) {
		area[x][y] = 1;
		res++;
		
		for(int i=0; i<4; i++) {
			int nx = x +dx[i];
			int ny = y +dy[i];
			
			if(nx>=0 && nx <m && ny >=0 && ny <n) {
				if(area[nx][ny] ==0) {
					dfs(nx,ny);
				}
			}
		}
	}

	static void makeSquare(int lx, int ly, int rx, int ry) {
		for (int i = ly; i < ry; i++) {
			for (int j = lx; j < rx; j++) {
				area[i][j] = 1;
			}
		}
	}
}
