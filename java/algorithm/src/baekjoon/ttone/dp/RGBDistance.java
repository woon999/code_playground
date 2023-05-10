package baekjoon.ttone.dp;

// #1149 dp RGB 거리 - 트리 dp 
import java.io.*;
import java.util.StringTokenizer;

public class RGBDistance {

	static int n;
	static int[][] dp, cost;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		cost = new int[n][3];
		dp = new int[n][3];
		StringTokenizer st = null;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<3; i++) {
			dp[0][i] = cost[0][i];
		}
		
//		일반 dp 풀이
//		for(int i=1; i<n; i++) {
//			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
//			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2])+ cost[i][1];
//			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1])+ cost[i][2];
//		}
		
		for(int i=0; i<3; i++) {
			solve(n-1, i);
		}
		
		int res = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
            res = Math.min(res, dp[n-1][i]);
		}
        System.out.print(res);
	}
	// 트리 dp 풀이 
	static void solve(int pos, int color) {
		if(pos ==0) return;
		if(dp[pos][color] != 0) return;
		
		int child = pos-1;
		int value = Integer.MAX_VALUE;
		for(int i=0; i<3; i++) {
			if(color != i) {
				solve(child, i);
				value = Math.min(value, dp[child][i]);
			}
		}
		dp[pos][color] = cost[pos][color] + value;
	}
}
