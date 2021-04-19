package baekjoon.ttzero.dynamicplanning3;

// #2133
import java.io.*;

public class GeneralTiling {
	
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		
		int answer =0;
		
		System.out.println(n%2 == 0 ? solve(n) : 0);
		
		
	}
	
	private static int solve(int n) {
		if(n ==0) return 1;
		if(n ==2) dp[2] = 3;
		else if (dp[n] == 0) {
			for(int i=2; i<n+1; i+=2) {
				int idx = i ==2 ? 3:2;
				dp[n] += idx * solve(n-i);
			}
		}
		
		return dp[n];
	}
}
