package baekjoon.dynamicplanning3;

import java.io.*;
import java.util.StringTokenizer;

public class Sticker {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[n+1][2];
			int[][] dp = new int[n+1][2];
			
			for(int y=0; y<2; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int x=1; x<n+1; x++) {
					arr[x][y] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[1][0] = arr[1][0];
			dp[1][1] = arr[1][1];
			
			for(int j=2; j<n+1; j++) {
				dp[j][0] = Math.max(dp[j-1][1], dp[j-2][1]) + arr[j][0];
				dp[j][1] = Math.max(dp[j-1][0], dp[j-2][0]) + arr[j][1];
			}
			
			System.out.println(Math.max(dp[n][0], dp[n][1]));
		}
	}
}
