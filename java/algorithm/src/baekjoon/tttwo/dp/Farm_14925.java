package baekjoon.tttwo.dp;

// #14925 dp 목장 건설하기  
import java.io.*;
import java.util.*;

public class Farm_14925 {
	
	static int[][] map, dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		map = new int[m+1][n+1];
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[m+1][n+1];
		int answer = -1, res = -1;
		for(int i=1; i<=m; i++) {
			for(int j=1; j<=n; j++) {
				if((res = checkIsSquare(j, i)) != -1) {
					answer = Math.max(res, answer);
				}
			}
		}
		
		System.out.println(answer);
		
	}
	
	static int checkIsSquare(int x, int y) {
		if(map[y][x] > 0) return -1;
		return dp[y][x] = Math.min(Math.min(dp[y-1][x], dp[y][x-1]), dp[y-1][x-1]) + 1;
		
	}
}
