package baekjoon.ttone.dp;

// #2449 dp 전구 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Light {
	
	static int INF = 987654321;
	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		dp = new int[n][n];
		for(int i=0; i<n; i++) {
			Arrays.fill(dp[i], INF);
		}
		// k : 1~20
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(solve(0,n-1));
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int a = dp[i][j] == INF ? -1 : dp[i][j];
				System.out.print(a +" ");
			}
			System.out.println();
		}
				
		
	}
	
	static int solve(int s, int e) {
		if(dp[s][e] != INF) return dp[s][e];
		if(s==e) return 0;
		for(int i=s; i<e; i++) {
			int cnt =0;	
			if(arr[s] != arr[i+1]) {
//				System.out.println(s+ ", " + e);
				cnt=1;
			}
			dp[s][e] = Math.min(dp[s][e],solve(s,i) + solve(i+1, e)+cnt);
		}
		
		return dp[s][e];
	}
}
