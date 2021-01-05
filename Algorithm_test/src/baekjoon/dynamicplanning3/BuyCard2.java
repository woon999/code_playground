package baekjoon.dynamicplanning3;

// #16194

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BuyCard2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] card = new int[1001];
		int[] dp = new int[10001];
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<n+1; i++) {
			dp[i] = card[i];
			
			for(int j=1; j<i+1; j++) {
				dp[i] = Math.min(dp[i], dp[i-j]+card[j]);
			}
		}
		
		
		System.out.println(dp[n]);
	}
}
