package baekjoon.ttzero.dynamicplanning3;

// #1495
import java.io.*;
import java.util.StringTokenizer;

public class Guitarlist {
	
	static int n,s,m;
	static int[] volume;
	static boolean[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		volume = new int[n+1];
		dp = new boolean[n+1][m+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			volume[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][s] = true;
		
		for(int i=1; i<n+1; i++) {
			for(int v=0; v<m+1; v++) {
				if(dp[i-1][v]) {
					if(v + volume[i] < m+1) {
						dp[i][v + volume[i]] = true;
					}
					if( v- volume[i] >= 0) {
						dp[i][v-volume[i]] = true;
					}
				}
			}
		}
		
		
		int res =-1;
		
		for(int i=0; i<m+1; i++) {
			if(dp[n][i]) {
				res = Math.max(res, i);
			}
		}
		
		System.out.println(res);
	}
}
