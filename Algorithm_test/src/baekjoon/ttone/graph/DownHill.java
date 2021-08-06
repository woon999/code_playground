package baekjoon.ttone.graph;

// #1520 grpah (1520. 내리막길) - dfs, dp 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DownHill {

	static int n,m;
	static int[][] map, dp;	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dp = new int[n][m];

		for (int i = 0; i<n; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(dfs(0,0));
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
	}
	static int dfs(int x, int y) {
		if(x == n-1 && y == m-1) return 1;
		if (dp[x][y] != -1) return dp[x][y];
		
		int pos = map[x][y];
		dp[x][y] = 0;
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx <0 || nx > n-1 || ny < 0 || ny > m-1) continue;
			
			int nxt = map[nx][ny];
			if (pos > nxt) {
				dp[x][y] += dfs(nx, ny);
			}
		}
		return dp[x][y];
	}
}
