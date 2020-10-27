package baekjoon.dynamicplanning3;

// #1010

import java.io.*;
import java.util.StringTokenizer;

public class Bridge {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			
			int[][] dp = new int[n+1][m+1];
			
			
			for(int a=0; a<n+1; a++) {
				dp[a][1] = 0;
			}
			
			for(int b=0; b<m+1; b++) {
				dp[1][b] = b;
			}
			
			for(int x=2; x<n+1; x++) {
				for(int y=2; y<m+1; y++) {
					dp[x][y] = dp[x][y-1] + dp[x-1][y-1];
				}
			}
			
			System.out.println(dp[n][m]);
		}
	}
}
