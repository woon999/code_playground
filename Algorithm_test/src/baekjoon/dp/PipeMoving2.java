package baekjoon.dp;

// #17069 dp 파이프 옮기기2 
import java.io.*;
import java.util.StringTokenizer;

public class PipeMoving2 {
	
	static int n;
	static int[][] map;
	static long[][][] dp;
	static boolean[][][] checked;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n+1][n+1];
		dp = new long[n+1][n+1][4];
		checked = new boolean[n+1][n+1][4];
		
		StringTokenizer st;
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<n+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(1,1,1));
		
		
//		dp[1][2][1] =1;  //초기 파이프 가로방향 위치
//		for(int i=1;i<n+1;i++) {
//			for(int j=3;j<n+1;j++) {
//				if(map[i][j] ==1) continue;
//				
//				//가로
//				dp[i][j][1] = dp[i][j-1][1]+dp[i][j-1][2]; 
//				//세로
//				dp[i][j][3] = dp[i-1][j][2] + dp[i-1][j][3]; 
//				//대각선
//				if(map[i][j-1] ==0 && map[i-1][j] ==0)  {
//					dp[i][j][2] = dp[i-1][j-1][1] + dp[i-1][j-1][2] + dp[i-1][j-1][3];
//				}
//				
//					
//			}
//		}
//		System.out.println(dp[n][n][1]+dp[n][n][2]+dp[n][n][3]);
	}
	
	static long dfs(int x, int y, int flow) {
		
		if(x<1 || y<1 || x>n || y>n || map[y][x] ==1) return 0;
		if(flow == 3 && (map[y-1][x] ==1 || map[y][x-1] ==1)) return 0;
		
		if(checked[y][x][flow]) return dp[y][x][flow];
		
		if(x==n && y== n) {
			return 1;
		}
		
		dp[y][x][flow] =0;
		checked[y][x][flow] = true;
		
		if(x==1&& y==1) {
			return dp[y][x][flow] += dfs(x+1,y,1);
		}
		
	
		// 가로 
		if(flow==1) {
			dp[y][x][flow] +=dfs(x+1,y,1) +
						dfs(x+1,y+1,3);
		}
		// 세로 
		else if(flow==2) {
			dp[y][x][flow] += dfs(x,y+1,2)+
						dfs(x+1,y+1,3);
		}
		// 대각선 
		else if(flow==3) {
			dp[y][x][flow] += dfs(x+1,y,1) +
						dfs(x,y+1,2) +
						dfs(x+1,y+1,3);
		}
		
		return dp[y][x][flow];
			
		
	}

}
