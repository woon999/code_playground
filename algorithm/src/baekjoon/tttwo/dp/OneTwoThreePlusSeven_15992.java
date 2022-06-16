package baekjoon.tttwo.dp;

// #15992 dp 1,2,3 더하기 7 
import java.io.*;
import java.util.*;

public class OneTwoThreePlusSeven_15992 {

	static final int MOD = (int)1e9+9;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] dp = new int[1001][1001];
		dp[1][1] = 1; // 1
		dp[2][1] = 1; // 2 
		dp[2][2] = 1; // 1+1
		dp[3][1] = 1; // 3 
		dp[3][2] = 2; // 1+2, 2+1 
		dp[3][3] = 1; // 3
		
		for(int i=4; i<=1000; i++) {
			for(int j=1; j<=1000; j++) { 
				dp[i][j] = ((dp[i-1][j-1] + dp[i-2][j-1]) % MOD + dp[i-3][j-1]) % MOD;
			}
		}
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			// n을 m개 
			System.out.println(dp[n][m]);
		}
	}
}
