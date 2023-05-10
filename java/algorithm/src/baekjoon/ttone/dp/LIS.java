package baekjoon.ttone.dp;

// #11053 dp LIS 가장 긴 증가하는 부분 수열 
import java.io.*;
import java.util.StringTokenizer;

public class LIS {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		
		dp[0] = 1;
		for(int i=1; i<n; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
//				if(arr[i] > arr[j] && dp[j] >= dp[i]) {
//					dp[i] = dp[j] +1;
//				}
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			
		}
		int max =-1;
		for(int i=0; i<n; i++) {
			max = Math.max(max, dp[i]);
			System.out.print(dp[i]+" ");
		}
		System.out.println();
		
		System.out.println(max);
	}
}
