package baekjoon.ttone.graph;

// #2468 안전영역 (dfs, 브루트포스 ) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SafeZone {

	static int n;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0,- 1, 0, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		
		
		
		int maxHeight=0;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > maxHeight) {
					maxHeight =map[i][j];
				}
			}
		}
		
		int max =0;
		for(int height=0; height<maxHeight+1; height++) {
			check = new boolean[n][n];
			int cnt=0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					if(!check[i][j] && map[i][j] > height){
						cnt+=dfs(i,j,height);
					}
					
				}
			}
			max = Math.max(max, cnt);
		}
		System.out.println(max);
	}
	
	static int dfs(int x, int y, int height) {
		
		check[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x +dx[i];
			int ny = y +dy[i];
			
			if(nx<0 || ny<0 || nx>n-1 || ny >n-1) continue;
			if(check[nx][ny]) continue;
			
			if(map[nx][ny]> height) {
				dfs(nx,ny, height);
			}
		}
		
		return 1;
	}
	
	// bfs (느림) 
	static int bfs(int x, int y, int height) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[]{x,y});
		check[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = px +dx[i];
				int ny = py +dy[i];
				
				if(nx<0 || ny<0 || nx>n-1 || ny >n-1) continue;
				if(check[nx][ny]) continue;
				
				if(map[nx][ny]> height) {
					check[nx][ny] = true;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
		return 1;
	}
}
