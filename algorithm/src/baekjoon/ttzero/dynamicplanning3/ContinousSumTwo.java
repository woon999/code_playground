package baekjoon.ttzero.dynamicplanning3;

// #13398
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ContinousSumTwo {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[][] dp = new int[n][2];
		dp[0][0] = dp[0][1] = arr[0];
		int max = arr[0];
	
		for(int i=1; i<n; i++) {
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
			
			
			// 1. i번째 수가 제거되는 경우
			// 2. i번째 수 전에 지워진 수가 있는 경우
			dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]);
			
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		
//		for(int i=0; i<n; i++) {
//			System.out.print(dp[i][0]+ " ");
//			
//		}
//		System.out.println();
//		for(int i=0; i<n; i++) {
//			System.out.print(dp[i][1]+ " ");
//		}
		System.out.println(max);
	}
}
