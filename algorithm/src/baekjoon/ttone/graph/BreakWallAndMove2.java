package baekjoon.ttone.graph;

// #14442 graph 벽 부수고 이동하기 2 - BFS 
import java.io.*;
import java.util.*;

public class BreakWallAndMove2 {

	static int n,m,k;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			String line = br.readLine();
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(line.charAt(j)+"");
			}
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][][] check = new boolean[n][m][k+1];
		q.add(new int[] {0,0,k,1});
		
		int cnt = -1;
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int px = p[0], py = p[1];
			int chance = p[2], move = p[3];
			
			if(px==m-1 && py == n-1) {
				return cnt = move;
			}
			for(int i=0; i<4; i++) {
				int nx = px+dx[i];
				int ny = py+dy[i];
				
				if(nx<0|| ny<0 || nx>m-1 || ny>n-1 || check[ny][nx][chance]) continue;
				
				if(map[ny][nx] == 0) {
					check[ny][nx][chance] = true;
					q.add(new int[] {nx, ny, chance, move+1});	
				}else {
					if(chance >0) {
						check[ny][nx][chance] = true;
						q.add(new int[] {nx, ny, chance-1, move+1});
					}
				}
			}
		}
		return cnt;
	}
}
