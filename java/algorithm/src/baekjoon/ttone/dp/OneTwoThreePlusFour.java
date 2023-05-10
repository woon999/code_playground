package baekjoon.ttone.dp;

// #15989 dp 1,2,3 더하기 4 
import java.io.*;
import java.util.Arrays;

public class OneTwoThreePlusFour {

	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		dp = new int[100001][4];
		
		for(int i=0; i<100001; i++) {
			Arrays.fill(dp[i], -1);
			
		}
		
		dp[1][1] =1;
		dp[2][1] =1;
		dp[2][2] =1;
		dp[3][1] =1;
		dp[3][2] =1;
		dp[3][3] =1;
		
		for(int i=4; i<10001; i++) {
			dp[i][1] = dp[i-1][1];
			dp[i][2] = dp[i-2][1]+ dp[i-2][2];
			dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3];
		}
		
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			System.out.println(dp[num][1] + dp[num][2] + dp[num][3]);
			
		}
	}
	
	
}
