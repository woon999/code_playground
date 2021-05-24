package baekjoon.ttone.graph;

// #2146 graph 다리 만들기 
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class MakeBridge {

	static int n;
	static int[][] map;
	static boolean[][] check;
	static Queue<int[]> q;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		check = new boolean[n][n];
		q = new LinkedList<>();
		
		
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		IslandCount();
		
		
		int min = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] > 0) {
					check = new boolean[n][n];
					int res = bridge(j,i);
					
					if(res == -1 )continue;
					if(min > res) {
						min = res;
//						System.out.println("다리 완성 " + res );
					}
				}
			}
		}
		System.out.println(min-1);
	}
	
	static int bridge(int x, int y) {
		q = new LinkedList<>();
		
		int num = map[y][x];
		check[y][x] =true;
		q.add(new int[]{x,y,0});
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0];
			int py = pos[1];
			int bridge = pos[2];
			
			if(map[py][px] != 0 && map[py][px] != num) {
				return bridge;
			}
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || nx>n-1 || ny <0 || ny>n-1) continue;
				if(check[ny][nx] ||map[ny][nx] == num) continue;
				
				check[ny][nx] = true;
				q.add(new int[] {nx,ny, bridge+1});
			}
			
		}
		return -1;
		
	}
	
	static void IslandCount() {

		int idx = 2; 
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!check[i][j] && map[i][j] != 0) {
					map[i][j] = idx;
					check[i][j] =true;
					q.add(new int[] {j,i});
					
					while(!q.isEmpty()) {
						int[] pos = q.poll();
						int px = pos[0];
						int py = pos[1];
						
						for(int d=0; d<4; d++) {
							int nx = px + dx[d];
							int ny = py + dy[d];
							
							if(nx<0 || nx>n-1 || ny <0 || ny>n-1) continue;
							if(check[ny][nx]) continue;
							
							if(map[ny][nx] == 1) {
								check[ny][nx] = true;
								map[ny][nx] = idx;
								q.add(new int[] {nx,ny});
							}
						}
					}
					idx++;
				}
			}
			
		}
		
	}
}
