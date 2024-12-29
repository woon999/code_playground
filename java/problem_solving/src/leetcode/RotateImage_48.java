package leetcode;

// #48 - Rotate Image

public class RotateImage_48 {
	public static void main(String[] args) {
		int[][] matrix ={
			{1,2,3}, {4,5,6}, {7,8,9}
		};
		rotate(matrix);
	}

	public static void rotate(int[][] matrix) {
		// 좌우 변경
		// 123   321
		// 456 > 654
		// 789   987
		for(int i=0; i<matrix[0].length; i++){
			int s = 0, e = matrix[0].length-1;
			while(s<e){
				int tmp = matrix[i][s];
				matrix[i][s] = matrix[i][e];
				matrix[i][e] = tmp;
				s++;
				e--;
			}
		}

		// 대각선 변경
		// 321   741
		// 654 > 852
		// 987   963
		int row = matrix.length-1;
		for(int i=0; i<matrix.length; i++){
			int r = 0;
			for(int j=matrix.length-1; j>=i; j--){
				int tmp = matrix[i][r];
				matrix[i][r] = matrix[j][row];
				matrix[j][row] = tmp;
				r++;
			}
			row--;
		}
	}

	static void print(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
}
