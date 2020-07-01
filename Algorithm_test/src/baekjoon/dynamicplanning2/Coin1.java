package baekjoon.dynamicplanning2;

// #2293

import java.io.*;
import java.util.*;

public class Coin1 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int coin;
		int[] dp = new int[k+1];
		
		dp[0]=1;
		for(int i =0; i<n;i++) {
			coin = Integer.parseInt(br.readLine());
			for(int j=1; j<k+1; j++) {
				if(j>=coin)
				dp[j] += dp[j-coin];
			}
//			System.out.println(Arrays.toString(dp));
		}
		System.out.println(dp[k]);
		
	}
}
