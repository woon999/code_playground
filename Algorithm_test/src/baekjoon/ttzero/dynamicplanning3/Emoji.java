package baekjoon.ttzero.dynamicplanning3;

// #14226
import java.io.*;
import java.util.*;

public class Emoji {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][n+1];
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(1);
		q.add(0);
		dp[1][0] = 0;
		while(!q.isEmpty()) {
			int s = q.poll();
			int c = q.poll();
			
			if(dp[s][s] == -1) {
				dp[s][s] = dp[s][c] +1;
				q.add(s); q.add(s);
			}
			if(s+c < n+1 && dp[s+c][c] == -1) {
				dp[s+c][c] = dp[s][c] +1;
				q.add(s+c); q.add(c);
			}
			
			if(s-1 >=0 && dp[s-1][c] == -1) {
				dp[s-1][c] = dp[s][c] +1;
				q.add(s-1); q.add(c);
			}
		}
		
		int res = -1;
		for(int i=0; i<n+1; i++) {
			if(dp[n][1] != -1) {
				if(res == -1 || res > dp[n][i]) {
					res = dp[n][i];
				}
			}
		}
		
		System.out.println(res);
	}
}
