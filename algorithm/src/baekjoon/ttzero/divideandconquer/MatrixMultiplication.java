package baekjoon.ttzero.divideandconquer;

// #2740
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MatrixMultiplication {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr1= new int[n][m];
		
		for(int i =0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				arr1[i][j] =Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] arr2 = new int[m][k];
		
		for(int i =0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<k; j++) {
				arr2[i][j] =Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][] mulArr = new int[n][k];
		
		for(int i =0; i<n; i++) {
			for(int j=0; j<k; j++) {
				for(int l=0; l<m; l++) {
				mulArr[i][j] += arr1[i][l] * arr2[l][j]; 
				}
			}
		}
		
		for(int i =0; i<n; i++) {
			for(int j=0; j<k; j++) {
				System.out.print(mulArr[i][j]);
				System.out.print(' ');
			}
			System.out.println();
		}
		
		
	}
}
