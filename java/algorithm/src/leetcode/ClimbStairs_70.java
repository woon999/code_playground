package leetcode;

// #70 - Climb Stairs

public class ClimbStairs_70 {
	public static void main(String[] args) {
		int n = 3;

		System.out.println("climbStairs(n) = " + climbStairs(n));
	}

	// 1: 1
	// 2: 1+1, 2
	// 3: (1+1)+1, (2)+1, (1)+2
	// 4: (1+1+1)+1, (2+1)+1, (1+1)+2, (2)+2
	// dp[1] = 1, dp[2] = 2
	// dp[n] = dp[n-1] + dp[n-2]
	public static int climbStairs(int n) {
		int[] dp = new int[n+1];
		dp[0] = dp[1] = 1;
		for(int i=2; i<=n; i++){
			dp[i] = dp[i-1]+dp[i-2];
		}
		return dp[n];
	}
}
