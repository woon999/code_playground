package leetcode;

// #363 - Max Sum of Rectangle No Larger Than K

public class MaxSumofRectangleNoLargerThanK_363 {
	public static void main(String[] args) {
		int[][] matrix = {{1, 0, 1}, {0, -2, 3}};
		int k = 2;
		// int[][] matrix = {{2, 2, -1}};
		// int k = 0;

		System.out.println("maxSumSubmatrix(matrix, k) = " + maxSumSubmatrix(matrix, k));
	}

	public static int maxSumSubmatrix(int[][] matrix, int k) {
		int n = matrix.length; // y
		int m = matrix[0].length; // x
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			int[] tmp = new int[m];
			for (int j = i; j < n; j++) {
				for (int l = 0; l < m; l++) {
					tmp[l] += matrix[j][l];
				}
				int sum = getMaxSum(tmp, k);
				max = Math.max(max, sum);
			}
		}
		return max;
	}

	public static int getMaxSum(int[] tmp, int k) {
		int ans = Integer.MIN_VALUE;

		for (int i = 0; i < tmp.length; i++) {
			int s = 0;
			for (int j = i; j < tmp.length; j++) {
				s += tmp[j];
				if (s <= k) ans = Math.max(ans, s);
			}
		}
		return ans;
	}
}
