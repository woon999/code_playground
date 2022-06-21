package baekjoon.tttwo.dp;

// #4781 dp 사탕 가게 

import java.io.*;
import java.util.*;

public class CandyShop_4781 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = (int)(Double.parseDouble(st.nextToken())*100 +0.5);
			
			if(n==0 && m==0) {
				break;
			}
			
			int[] dp = new int[m+1];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int p =  (int)(Double.parseDouble(st.nextToken())*100+0.5);
				if(p > m) continue;
				
				for(int j=p; j<=m; j++) {
					dp[j] = Math.max(dp[j], dp[j-p]+c);
				}
			}
			sb.append(dp[m]+"\n");
		}
		System.out.println(sb.toString());
	}
}
