package baekjoon.dp;

// #2698 dp 인접한 비트의 개수  
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberOfAdjacencyBit {

	static int n,k;
	static int[][][] dp;
	static int cnt =0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st = null;
		
		dp = new int[100][100][2];
		
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
//		dp[1][0][1] =1; // 1
//		dp[1][0][0] =1; // 0
//		for(int k=0; k<100; k++) {
//			for(int n=2; n<101; n++) {
//				if(k==0) {
//					dp[n][k][1] += dp[n-1][k][0];
//				}else {
//					dp[n][k][1] += dp[n-1][k][0] + dp[n-1][k-1][1];
//				}
//				dp[n][k][0] += dp[n-1][k][0] + dp[n-1][k][1];
//			}
//		}
			
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());			
			
//			System.out.println(dp[n][k][0] + dp[n][k][1]);
			int a = solve(n-1,k,0);
			int b = solve (n-1,k,1);
//			System.out.println("cnt: " + cnt);
			System.out.println(a+b);
		}
		
	}
	
	// top-down
	static int solve(int len, int adNum, int bit) {
		
		if(len ==0) { 
			if (adNum ==0) return 1;
			return 0;
		}
		
		if(dp[len][adNum][bit] != -1) return dp[len][adNum][bit]; 
		
		int res =0;
		if(adNum==0) {
			if(bit ==1) {
				res += solve(len-1, adNum, 0);
			}else {
				res += solve(len-1, adNum, 1);
				res += solve(len-1, adNum, 0);
			}
			
		}else {
			if(bit ==1) {
				res += solve(len-1, adNum-1, 1);
				res += solve(len-1, adNum, 0);
			}else {
				res += solve(len-1, adNum, 1);
				res += solve(len-1, adNum, 0);
			}
			dp[len][adNum][bit] = res;
			cnt++;
			System.out.println(res);
		}
		
		return res;
		
	}
}
