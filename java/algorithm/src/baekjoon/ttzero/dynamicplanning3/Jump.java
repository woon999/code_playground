package baekjoon.ttzero.dynamicplanning3;

// #1890
import java.io.*;
import java.util.StringTokenizer;

public class Jump {
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		long[][] dp = new long[n][n];
 		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] =1;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(i==n-1 && j==n-1) continue;
				
				if(i + arr[i][j] < n) {
					dp[i + arr[i][j]][j] += dp[i][j];
				}
				
				if(j + arr[i][j] < n) {
					dp[i][arr[i][j] +j] += dp[i][j];
				}
			}
		}
		
		System.out.println(dp[n-1][n-1]);
	}

}
