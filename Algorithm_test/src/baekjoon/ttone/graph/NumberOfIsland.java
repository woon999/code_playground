package baekjoon.ttone.graph;

// #4963 섬의 개수 (dfs) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class NumberOfIsland {

	static int w,h;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = {0,1,1,1,0,-1,-1,-1};
	static int[] dy = {1,1,0,-1,-1,-1,0,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line;
		String end = "0 0";
		while(!(line = br.readLine()).equals(end)) {
			StringTokenizer st = new StringTokenizer(line);
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new int[h][w];
			checked = new boolean[h][w];

			for(int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count=0;
			for(int i=0; i<h; i++) {
				for(int j=0; j<w; j++) {
					if(map[i][j] ==1 && !checked[i][j]) {
						count += dfs(j,i);
					}
				}
			}
			System.out.println(count);
			
		}
	}
	
	static int dfs(int x, int y) {
		
		checked[y][x] =true;
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx <0 || ny <0 || nx > w-1 || ny >h-1) continue;
			if(checked[ny][nx]) continue;
			
			if(map[ny][nx]==1) {
				dfs(nx,ny);
				
			}
		}
		
		return 1;
	}
}
