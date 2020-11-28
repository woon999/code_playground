package baekjoon.dynamicplanning3;

// #11055
import java.io.*;
import java.util.StringTokenizer;

public class LongestIncrementalPartialSequence {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		int[] cost = new int[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] =cost[1];
		
		for(int i=2; i<n+1; i++) {
			dp[i] = cost[i];
			for(int j=1; j<i; j++) {
				if(cost[i] > cost[j]) {
					dp[i] = Math.max(dp[j] + cost[i], dp[i]);
				}
			}
		}
		
		int max = 0;
		for(int i=1; i<n+1; i++) {
			max = (dp[i] >max) ? dp[i] : max;
		}
	
		System.out.println(max);
	}
	
}
