package baekjoon.dynamicplanning3;

// #2225
import java.io.*;
import java.util.StringTokenizer;

public class Summation {

	static int size = 201;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[size][size];
		
		for(int i=0; i<k+1; i++) {
			dp[i][0] = 1;
		}
		
		for(int i=1; i<k+1; i++) {
			for(int j=1; j<n+1; j++) {
					dp[i][j] += (dp[i][j-1] + dp[i-1][j]) % 1_000_000_000;
			}
		}
		
		System.out.println(dp[k][n]);
	}
}
