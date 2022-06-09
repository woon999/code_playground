package baekjoon.tttwo.dp;

// #1106 dp 호텔 
import java.io.*;
import java.util.*;

public class Hotel_1106 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int c = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		List<int[]> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new int[] {Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken())});
		}
		
		int[] dp = new int[100_001];
		for(int i=1; i<100_001; i++) {
			for(int[] v : list)	{
				int cost = v[0];
				int client = v[1];
				if(i%cost ==0) {
					dp[i] = Math.max(dp[i], (i/cost) * client);
				}
				
				if(i-cost >= 0) {
					dp[i] = Math.max(dp[i], dp[i-cost] + client);
				}
			}
			
			if(dp[i] >= c) {
				System.out.println(i);
				return;
			}
		}
	}
}
