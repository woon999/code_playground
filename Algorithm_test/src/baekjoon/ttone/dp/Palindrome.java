package baekjoon.ttone.dp;

// #10942 dp,문자열 펠린드롬  
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Palindrome {

	static int n;
	static int[] num;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		num = new int[n+1];
		dp = new int[n+1][n+1];
		for(int i=0; i< n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			boolean flag = checkPalin(start, end) == 1? true : false;
			
			if(flag) {
				sb.append("1\n");
			}else {
				sb.append("0\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	

	static int checkPalin(int s, int e) {
		if( s >= e) return 1;
		
		if(dp[s][e] != -1) return dp[s][e];
		
		if(num[s]==num[e]) {
			return dp[s][e] = checkPalin(s+1, e-1);
		}
		return 0;
	
	}
}

