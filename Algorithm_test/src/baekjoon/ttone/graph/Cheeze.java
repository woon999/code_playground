package baekjoon.ttone.graph;

// #2638 graph 치즈 - dfs, bfs 
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Cheeze {

	static int n,m;
	static boolean[][] checked;
	static int[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		map[0][0] = 9;
		int cycle =0;
		while(true) {
			outsideAirIndexing();
			
			checked = new boolean[n][m];
			int cnt=0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					if(map[i][j] == 1 && !checked[i][j]) {
						cnt += melting(j,i);
					}
				}
			}
			
			if(cnt ==0) break;
			mapUpdate();
			
			cycle++;
//			System.out.println("-----"+cycle+"-------");
//			printMap();
		}
		
		System.out.println(cycle);
		
	}
	
	static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void mapUpdate() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(map[i][j] == -1) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void outsideAirIndexing() {
		Queue<int[]> q = new LinkedList<>();
		checked = new boolean[n][m];
		
		q.add(new int[] {0,0});
		checked[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int px = pos[0], py = pos[1];
			
			for(int i=0; i<4; i++) {
				int nx = px + dx[i];
				int ny = py + dy[i];
				
				if(nx<0 || nx>m-1 || ny<0 || ny>n-1 || checked[ny][nx]) continue;
				
				if(map[ny][nx] != 1) {
					map[ny][nx] = 9;
					checked[ny][nx] = true;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
	
	static int melting(int x, int y) {
		checked[y][x] = true;
		if(map[y][x] ==1 && isAvailableMelt(x, y))	{
			map[y][x] = -1;
			return 1;
		}
		
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>m-1 || ny<0 || ny>n-1 || checked[ny][nx]) continue;
			
			if(map[ny][nx] == 1) {
				melting(nx, ny);
			}
		}
		return 0;
	}
	
	static boolean isAvailableMelt(int x, int y) {
		int cnt =0;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>m-1 || ny<0 || ny>n-1) continue;
			if(map[ny][nx] == 9) cnt++;
		}
		
		if(cnt>=2) return true;
		return false;
	}
}

//8 9
//0 0 0 0 0 0 0 0 0
//0 0 0 0 0 0 0 0 0
//0 1 1 0 0 0 1 1 0
//0 1 0 1 1 1 0 1 0
//0 1 0 0 1 0 0 1 0
//0 1 0 1 1 1 0 1 0
//0 1 1 0 0 0 1 1 0
//0 0 0 0 0 0 0 0 0
