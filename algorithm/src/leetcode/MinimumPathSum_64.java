package leetcode;

// #64 - Minimum Path Sum
import java.util.Arrays;

public class MinimumPathSum_64 {
	public static void main(String[] args) {
		// int[][] grid = {
		// 	{1, 3, 1},
		// 	{1, 5, 1},
		// 	{4, 2, 1}
		// };
		int[][] grid = {
			{1, 2, 3},
			{4, 5, 6}
		};

		System.out.println("minPathSum(grid) = " + minPathSum(grid));
	}

	public static int minPathSum(int[][] grid) {
		int m = grid.length;
		int n = grid[0].length;

		for(int i=0; i<m; i++){
			for(int j=0; j<n; j++){
				if(i ==0 && j == 0) continue;
				else if(i ==0){
					grid[i][j] += grid[i][j-1];
				}else if(j ==0){
					grid[i][j] += grid[i-1][j];
				}else {
					grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
				}
			}
		}

		for(int i=0; i<m; i++){
			System.out.println(Arrays.toString(grid[i]));
		}

		return grid[m-1][n-1];
	}
}
