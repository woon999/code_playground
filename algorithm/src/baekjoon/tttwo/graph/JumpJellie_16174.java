package baekjoon.tttwo.graph;

// #16174 graph 점프왕 쩰리 - bfs 
import java.io.*;
import java.util.*;

public class JumpJellie_16174 {

	static int n;
	static int[][] map;
	static int[] dx = {1, 0};
	static int[] dy = {0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		bfs(0,0);
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q =new LinkedList<>();
		q.add(new int[] {x,y});
		boolean[][] check = new boolean[n][n];
		check[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0], cy = cur[1];
			
			int jump = map[cy][cx];
			if(jump  == -1) {
				System.out.println("HaruHaru");
				return;
			}
			
			for(int i=0; i<2; i++) {
				int nx = cx + jump * dx[i];
				int ny = cy + jump * dy[i];
				
				if(nx < 0 || ny < 0 || nx > n-1 || ny > n-1 || check[ny][nx]) continue;
				check[ny][nx] = true;
				q.add(new int[] {nx, ny});
			}
		}
		
		System.out.println("Hing");
	}
}
