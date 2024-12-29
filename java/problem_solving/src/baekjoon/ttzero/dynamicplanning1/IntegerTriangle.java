package baekjoon.ttzero.dynamicplanning1;

// #1932
import java.io.*;
import java.util.StringTokenizer;

public class IntegerTriangle {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int result = Integer.MIN_VALUE;
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n+1][n+1];
		
		for(int i =1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());

				if(j==1) {
					arr[i][j] = arr[i-1][j] + arr[i][j];
				}
				else if(i==j) {
					arr[i][j] = arr[i-1][j-1] + arr[i][j];
				}
				else {
					arr[i][j] = Math.max(arr[i-1][j-1],arr[i-1][j]) + arr[i][j];
				}
				
				result = Math.max(arr[i][j], result);
			}
		}
		
//		for(int i =1; i<n+1; i++) {
//			for(int j=1; j<i+1; j++) {	
//				System.out.print(arr[i][j]+" ");
//			}
//			System.out.println();
//		}
		
		System.out.println(result);
		
	}
	
}
