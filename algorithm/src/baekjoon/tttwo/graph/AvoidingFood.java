package baekjoon.tttwo.graph;

// #1743 graph 음식물 피하기 - bfs 
import java.io.*;
import java.util.*;

public class AvoidingFood {

	static int n, m;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static boolean[][] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		check = new boolean[n][m];
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			map[y][x] = 1;
		}
		
		int res = -1; 
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(!check[i][j]) {
					res = Math.max(res, bfs(j, i));
				}
			}
		}
		System.out.println(res);
	}
	
	static int bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		check[y][x] = true;
		q.add(new int[] {x, y});
		
		int cnt = 0;
		if(map[y][x] == 1) cnt++;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0];
			int py = p[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx < 0 || ny < 0 || nx > m-1 || ny > n-1 || check[ny][nx]) continue;
				if(map[ny][nx] == 1) {
					check[ny][nx] = true;
					cnt++;
					q.add(new int[] {nx, ny});
				}
			}
		}
		return cnt;
	}
	
}
