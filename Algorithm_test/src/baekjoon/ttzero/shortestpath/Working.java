package baekjoon.ttzero.shortestpath;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Working {
	
	static int INF = 567891234;
	static int v,e;
	static int[][] dp;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		dp = new int[v+1][v+1];
		
		for(int i=0; i<v+1; i++) {
			Arrays.fill(dp[i], INF);
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dp[a][b] = Math.min(dp[a][b], c);
		}
		
		
		floyd();
		
		int result = INF;
		
		for(int i=0; i<v+1; i++) {
			result = Math.min(dp[i][i], result);
		}
		if(result==INF) System.out.println(-1);
		else System.out.println(result);
	}
	
	static void floyd() {
		for(int i=1; i<v+1; i++) {
			for(int j=1; j<v+1; j++) {
				for(int k=1; k<v+1; k++) {
					if(dp[j][k] > dp[j][i]+ dp[i][k] ) {
						dp[j][k] = dp[j][i] + dp[i][k];
					}
				}
			}
		}
	}
	
	static void print() {
		for(int i=0; i<v+1; i++) {
			for(int j=0; j<v+1; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.println();
		}
	}
}
