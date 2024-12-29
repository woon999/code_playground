package baekjoon.tttwo.dp;

// #18353 dp 병사 배치하기 - LIS, DP
// 최대 N 2,000이기 때문에 LIS DP O(n^2)로 풀이 가능
// 가장 긴 증가하는 부분 수열의 길이를 구한 후 n으로 빼주면 제거해야 하는 병사의 수를 구할 수 있음 

import java.io.*;
import java.util.StringTokenizer;

public class DeployingSoldiers {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int[] dp = new int[n];
		dp[0] = 1;
		for(int i=1; i<n; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[i] < arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		int max = -1;
		for(int i=0; i<n; i++) {
			max = Math.max(max, dp[i]);
		}
		System.out.println(n-max);
	}
}
