package baekjoon.dynamicplanning2;

// #11049
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MatrixMultiplicationOrder {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n+1];
		int[][] dp = new int[n+1][n+1];

		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
			arr[i+1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=2; i<n+1; i++) {
			for(int j=1; j<=n-i+1; j++) {
				dp[j][j+i-1] =Integer.MAX_VALUE;
				for(int k=j; k<j+i-1; k++) {
					int mul = dp[j][k] + dp[k+1][j+i-1] + arr[j-1]* arr[k] * arr[j+i-1];
					dp[j][j+i-1] = Math.min(dp[j][j+i-1], mul);
				}
			}
			System.out.println(Arrays.deepToString(dp));
		}
		
		System.out.println(dp[1][n]);
		
	
	}
}
