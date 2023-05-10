package baekjoon.tttwo.dp;

// #12026 dp boj 거리 

import java.io.*;
import java.util.Arrays;

public class BOJDistance_12026 {

	static final int INF = 987654321;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		char[] arr = br.readLine().toCharArray();
		int[] dp = new int[1001];
		Arrays.fill(dp, INF);
		dp[0] = 0;
		
		for(int i=0; i<n; i++) {
			if(arr[i] == 'B') {
				for(int j=i+1; j<n; j++) {
					if(arr[j] == 'O') {
						int jump = j-i;
						dp[j] = Math.min(dp[j], jump*jump + dp[i]);
					}
				}
			}else if(arr[i] == 'O') {
				for(int j=i+1; j<n; j++) {
					if(arr[j] == 'J') {
						int jump = j-i;
						dp[j] = Math.min(dp[j], jump*jump + dp[i]);
					}	
				}
			}else {
				for(int j=i+1; j<n; j++) {
					if(arr[j] == 'B') {
						int jump = j-i;
						dp[j] = Math.min(dp[j], jump*jump + dp[i]);
					}
				}
			}
		}
		
		System.out.println(dp[n-1] == INF? -1 : dp[n-1]);
		
		
		
	}
}
