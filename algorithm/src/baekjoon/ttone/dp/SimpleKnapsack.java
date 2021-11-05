package baekjoon.ttone.dp;

// #12865 dp 평범한 배낭 - knapsack 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SimpleKnapsack {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] w = new int[n];
		int[] v = new int[n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[n][k+1];
		for(int i=0; i<n; i++) {
			int weight = w[i];
			for(int j=0; j<=k; j++) {
				if(i==0) {
					if(weight<=j) dp[i][j] = v[i];
				}
				else {
					dp[i][j] = dp[i-1][j];
					if(weight<=j) {
						dp[i][j] = Math.max(dp[i-1][j-w[i]]+v[i], dp[i-1][j]);
					}
				}
			}
		}
		System.out.println(dp[n-1][k]);
	}
}
