package baekjoon.tttwo.dp;

// #14430 dp 자원 캐기 

import java.io.*;
import java.util.*;

public class ResourceDigging_14430 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n+1][m+1];
		int[][] dp = new int[n+1][m+1];
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=m; j++) {
				int prev = Math.max(dp[i-1][j], dp[i][j-1]);
				dp[i][j] =  Math.max(prev + map[i][j] , dp[i][j]);
			}
		}
		
		System.out.println(dp[n][m]);
		
	}
}
