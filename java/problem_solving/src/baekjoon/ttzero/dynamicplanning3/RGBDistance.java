package baekjoon.ttzero.dynamicplanning3;

// #17404
import java.io.*;
import java.util.*;

public class RGBDistance {
	static int n;
	static int[][] arr;
	static int[][] dp;
	static int INF = 543219876;
	static int result = INF;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1][3];
		dp = new int[n+1][3];
		
		
		for(int i=1; i<n+1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int j=0; j<3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<3; i++) {
			
			for(int j=0; j<3; j++) {
				if(i==j) dp[1][j] =arr[1][j];
				else dp[1][j] =INF;
			}
			
			
			for(int j=2; j<n+1; j++) {
				dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + arr[j][0];
				dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + arr[j][1];
				dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + arr[j][2];
			}
			
			for(int k=0; k<3; k++) {
				for(int j=0; j<n+1; j++) {
					System.out.print(dp[j][k]+" ");
				}
				System.out.println();
			}
			System.out.println("===================");
			for(int j=0; j<3; j++) {
				if(i != j) result = Math.min(result, dp[n][j]);
				
			}
		}
		
		System.out.println(result);
		
	}
}
