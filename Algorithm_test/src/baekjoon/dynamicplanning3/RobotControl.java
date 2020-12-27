package baekjoon.dynamicplanning3;

//#2169
import java.io.*;
import java.util.StringTokenizer;

public class RobotControl {

	static int n,m;
	static int[][] map;
	static int[][] dp;
	static int[][] tmp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m+1];
		dp = new int[n+1][m+1];
		tmp = new int[2][m+2];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<m+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[1][1] = map[1][1];
		
		for(int i=2; i<m+1; i++) {
			dp[1][i] += dp[1][i-1] + map[1][i];
		}
		
		for(int i=2; i<n+1; i++) {
			tmp[0][0] = dp[i-1][1];
			for(int j=1; j<m+1; j++) {
				tmp[0][j] = Math.max(tmp[0][j-1], dp[i-1][j]) + map[i][j];
			}
			
			tmp[1][m+1] = dp[i-1][m];
			for(int j=m; j>=1; j--) {
				tmp[1][j] = Math.max(tmp[1][j+1], dp[i-1][j]) + map[i][j];
			}
			
			for(int j=1; j<m+1; j++) {
				dp[i][j] = Math.max(tmp[0][j], tmp[1][j]);
			}
		}
		
		System.out.println(dp[n][m]);
		
		
		
	}
}
