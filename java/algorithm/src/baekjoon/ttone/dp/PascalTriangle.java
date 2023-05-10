package baekjoon.ttone.dp;

// #16395 dp 파스칼의 삼각형 
import java.io.*;
import java.util.StringTokenizer;

public class PascalTriangle {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[31][31];
		
		dp[1][1] =1;
		dp[2][1] =1;
		dp[2][2] =1;
		
		for(int i=3; i<n+1; i++) {
			for(int j=1; j<i+1; j++) {
				if(j==1 || j==i) {
					dp[i][j] = 1;
				}else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
