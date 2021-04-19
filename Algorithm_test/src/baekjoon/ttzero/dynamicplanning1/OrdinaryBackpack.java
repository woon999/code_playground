package baekjoon.ttzero.dynamicplanning1;

// #12865
import java.io.*;
import java.util.StringTokenizer;

public class OrdinaryBackpack {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] w= new int[n+1];
		int[] v= new int[n+1];
		
		int[][] dp = new int[n+1][k+1];
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			w[i] = Integer.parseInt(st.nextToken()); 
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i =1; i<n+1; i++) {
			for(int j=1; j<k+1; j++) {
				dp[i][j] = dp[i-1][j];
				if(j>=w[i]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		
		System.out.println(dp[n][k]);
	}
}
