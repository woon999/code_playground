package baekjoon.ttone.dp;

// #2491 수열 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sequence {
	
	static int[][] dp;
	static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n][2];
		dp[0][0] =1;
		dp[0][1] =1;
		
		int max =1;
		
		for(int i=1; i<n; i++) {
			// 증가 
			if(arr[i] >= arr[i-1]) {
				dp[i][0] = dp[i-1][0] +1;
			}else {
				dp[i][0] =1;
			}
			
			// 감소 
			if(arr[i] <= arr[i-1]) {
				dp[i][1] = dp[i-1][1] +1;
			}else {
				dp[i][1] =1;
			}
			
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(max);
	}
}
