package leetcode;

// #240 Search a 2D Matrix 2

public class SearchA2DMatrix2_240 {
	public static void main(String[] args) {

		int[][] matrix = {
			{1, 4, 7, 11, 15},
			{2, 5, 8, 12, 19},
			{3, 6, 9, 16, 22},
			{10, 13, 14, 17, 24},
			{18, 21, 23, 26, 30}
		};

		int target = 5;

		System.out.println("searchMatrix(matrix, target) = " + searchMatrix(matrix, target));
	}

	public static boolean searchMatrix(int[][] matrix, int target) {
		boolean flag = false;
		for (int y = 0; y < matrix.length; y++) {
			if (matrix[y][0] <= target && target <= matrix[y][matrix[0].length - 1]) {
				flag |= find(matrix, target, y);
				if(flag) return flag;
			}
		}

		return flag;
	}

	//binary search
	public static boolean find(int[][] matrix, int target, int y) {
		int x_left = 0;
		int x_right = matrix[0].length - 1;

		while (x_left <= x_right) {
			int x_mid = x_left + (x_right - x_left) / 2;
			if (matrix[y][x_mid] == target) {
				return true;
			} else if (matrix[y][x_mid] > target) {
				x_right = x_mid - 1;
			} else {
				x_left = x_mid + 1;
			}
		}
		return false;
	}
}
