package baekjoon.ttzero.dynamicplanning3;

// #9084
import java.io.*;
import java.util.StringTokenizer;

public class Coin3 {

	public static void main(String[] args) throws Exception{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st;
		 int t = Integer.parseInt(br.readLine());
		 
		 while(t -- >0) {
			 int n = Integer.parseInt(br.readLine());
			 int[] arr = new int[n+1];
			 
			 st = new StringTokenizer(br.readLine());
			 for(int i=1; i<n+1; i++) {
				 arr[i] = Integer.parseInt(st.nextToken());
			 }
			 
			 int m = Integer.parseInt(br.readLine());
			 int[] dp = new int[m+1];
			 
			 dp[0] =1;
			 for(int i=1; i<n+1; i++) {
				 for(int j= arr[i]; j < m+1; j++) {
					 dp[j] += dp[j - arr[i]];
				 }
			 }
			 System.out.println(dp[m]);
		 }
	}
}
