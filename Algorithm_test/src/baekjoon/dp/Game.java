package baekjoon.dp;


// #1103 dp 게임 (dp + dfs)
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Game {

	static int n,m;
	static int hole = -99;
	static int max=-1;
	static boolean flag= false;
	static int[][] map;
	static int[][] dp;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0, 1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		dp =  new int[n][m];
		visited = new boolean[n][m];
		
		for(int i=0; i<n; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<line.length; j++) {
				if(line[j].equals("H")) {
					map[i][j] = hole;
				}else {
					int num = Integer.parseInt(line[j]);
					map[i][j] = num;
				}
				
			}
			
		}
		

		visited[0][0] = true;
		dfs(0,0,1);
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
		
		if(flag) {
			System.out.println(-1);
		}else {
			System.out.println(max);
		}
	}
	
	
	static void dfs(int x, int y, int cnt) {
		
		if(cnt>max) {
			max = cnt;
		}
		dp[x][y] = cnt;
		
		for(int i=0; i<4; i++) {
			int move = map[x][y];
			
			int nx = x+ (move*dx[i]);
			int ny = y+ (move*dy[i]);
			
			if(nx<0 || ny<0 || nx>n-1 || ny>m-1 || map[nx][ny] == hole) {
				continue;
			}
			
			// 이미 방문한 곳을 다시 한번 방문하면 무한루프로 리턴 
			if(visited[nx][ny]) {
				flag = true;
				return;
			}
			
			// 이미 깊이 탐색한 부분 스킵
			if(dp[nx][ny] > cnt) continue;
			
			System.out.println("searching... " +  nx+","+ny);
			
			visited[nx][ny]= true;
			dfs(nx, ny, cnt+1);	
			visited[nx][ny]= false;
		}
		
	}
}
