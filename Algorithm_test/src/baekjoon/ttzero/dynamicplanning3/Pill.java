package baekjoon.ttzero.dynamicplanning3;

// #4811
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Pill {

	static long[][] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		dp = new long[31][31];
		dp[1][0] =1;
		dp[2][0] =2;
		dp[3][0] =5;
		
		solve(30, 0);
		
		int n = 0;
		while(true) {
			n = Integer.parseInt(br.readLine());
			
			if(n==0) break;
			
			System.out.println(dp[n][0]);
		}
				
	}
	
	static long solve(int w, int h) {
		
		if(w==0) return 1; // 반조각만 있을 경
		
		if(dp[w][h] != 0) return dp[w][h];
		
		long cnt =0;
		
		if(w != 0) {
			cnt += solve(w-1, h+1);
		}
		
		if(h != 0) {
			cnt += solve(w, h-1);
		}
		
		return dp[w][h] = cnt;
	}
}
