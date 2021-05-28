package baekjoon.ttone.dp;


// #10211 dp 최대부분배열문제 (dp) 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaximumSubarray2 {
	
	static int[] arr;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int test =0; test<t; test++) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n];
			dp = new int[n];	
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			solve();
		}
	}
	
	static void solve() {
		
		int max = arr[0];
		dp[0] = arr[0];
		for(int i=1; i<arr.length; i++) {
			if(dp[i-1] < 0) dp[i-1] =0;
			dp[i] = dp[i-1] + arr[i];
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
	
}
