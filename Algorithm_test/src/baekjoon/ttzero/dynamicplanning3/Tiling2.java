package baekjoon.ttzero.dynamicplanning3;

// #11727
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Tiling2{

	static int[] dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		dp = new int[n+1];
		
		dp[0] = 1;
		dp[1] = 1;
		
		System.out.println(solve(n));
	}
	
	//top-down
	static private int solve(int n) {
		
		
		if(dp[n] > 0) {
			return dp[n];
		}
		else {
			dp[n] = (solve(n-1) + solve(n-2)+ solve(n-2)) % 10007;
			return dp[n];
 		}
	}
}
