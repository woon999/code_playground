package baekjoon.dp;

import java.io.*;

// #2670 dp 연속 최대 곱 
public class ContinousMaxMul {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		double[] num = new double[n];
		double[] dp = new double[n];
		
		for(int i=0; i<n; i++) {
			num[i] = Double.parseDouble(br.readLine());
		}
		
		dp[0] = num[0];
		for(int i=1; i<n; i++) {
			dp[i] = ((dp[i-1]*num[i]) > num[i]) ? dp[i-1]*num[i] : num[i];
		}
		
		double max = 0;
		for(double i : dp) {
			max = Math.max(max, i);
//			System.out.println(String.format("%.3f", i));
		}
		
		System.out.println(String.format("%.3f", max));
		
		
	}
	
}
