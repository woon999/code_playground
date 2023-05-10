package baekjoon.tttwo.dp;

// #17216 dp 가장 큰 감소 부분 수열 

import java.io.*;
import java.util.*;

public class BigDecreaseSubArr_17216 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		int[] dp = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = arr[i];
		}
		
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<=i; j++) {
				if(arr[j] > arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
		}
		
		Arrays.sort(dp);
		System.out.println(dp[n-1]);
	}
}
