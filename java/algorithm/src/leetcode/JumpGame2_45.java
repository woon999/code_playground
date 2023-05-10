package leetcode;

// 45 - Jump Game 2

import java.util.Arrays;

public class JumpGame2_45 {
	public static void main(String[] args) {
		int[] nums = {2,3,1,1,4};

		System.out.println("jump(nums) = " + jump(nums));
	}

	public static int jump(int[] nums) {
		int n = nums.length;

		int[] dp = new int[n];
		Arrays.fill(dp, Integer.MAX_VALUE);

		dp[0] = 0;
		for(int i = 0; i< n; i++){
			int maxJump = nums[i];
			for(int j=i+1; j<=i+maxJump; j++){
				if(j >= n) break;
				dp[j] = Math.min(dp[i]+1, dp[j]);
			}
		}

		return dp[n-1];
	}
}
