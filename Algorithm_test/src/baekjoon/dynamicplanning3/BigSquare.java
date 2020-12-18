package baekjoon.dynamicplanning3;

// #1915
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BigSquare {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[n+1][m+1];
		int[][] dp = new int[n+1][m+1];
		
		
		for(int i=1; i<n+1;i++) {
			String str = br.readLine();
			for(int j=1; j<m+1; j++) {
				arr[i][j] = str.charAt(j-1) - '0';
			}
		}
		
		
		int res =0;
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<m+1; j++) {
				if(arr[i][j] == 1) {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) +1;
					res =Math.max(res, dp[i][j]);
				}
			}
		}
		
		
		System.out.println(res * res);
	}
}
