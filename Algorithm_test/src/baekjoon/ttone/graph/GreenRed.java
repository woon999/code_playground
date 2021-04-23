package baekjoon.ttone.graph;

//그래프 #10026 적록색약 (dfs) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GreenRed {

	static int	n;
	static int[][] map;
	static boolean[][] checked;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		checked = new boolean[n][n];
		int cnt=0;
		int rgCnt=0;
		
		for(int i=0; i<n; i++) {
			String[] color = br.readLine().split("");
			for(int j=0; j<n; j++) {
				
				if(color[j].equals("R")) {
					map[i][j] = 1;
				}else if(color[j].equals("G")) {
					map[i][j] = 2;
				}else {
					map[i][j] = 3;
				}
				
			}
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=1; k<4; k++) {
					if(!checked[i][j] && map[i][j] == k) {
						dfs(i,j,k);
						cnt++;
					}
				}
			}
		}
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] ==1 ) {
					map[i][j] = 2;
				}
			}
		}
		
		checked = new boolean[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=2; k<4; k++) {
					if(!checked[i][j] && map[i][j] == k) {
						dfs(i,j,k);
						rgCnt++;
					}
				}
			}
		}
		System.out.println(cnt+" "+rgCnt);
		
		
	}
	
	static void dfs(int x, int y, int color) {
		
		checked[x][y] = true;
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx <0 || ny <0 || nx>n-1 || ny>n-1) continue;
			if(checked[nx][ny]) continue;
			
			if(map[nx][ny] == color) {
				dfs(nx,ny, color);
			}
		}
		
	}
}
