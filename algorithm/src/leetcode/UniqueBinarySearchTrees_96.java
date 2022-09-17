package leetcode;

// #96 - Unique Binary Search Trees

public class UniqueBinarySearchTrees_96 {
	public static void main(String[] args) {
		int n = 3;
		System.out.println("numTrees(n) = " + numTrees(n));
	}

	public static int numTrees(int n) {
		int[] dp = new int[n + 1];
		return cal(n, dp);
	}

	public static int cal(int n, int[] dp) {
		if (n == 0 || n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		if (dp[n] != 0) {
			return dp[n];
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			ans += cal(i, dp) * cal(n - i - 1, dp);
		}
		return dp[n] = ans;
	}
}
