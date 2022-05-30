package baekjoon.tttwo.dp;

// #1695 dp 펠린드롬 만들기 

import java.io.*;
import java.util.*;

public class MakePalindrome_1695 {

	static int[] arr;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		arr = new int[5001];
		dp = new int[5001][5001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			Arrays.fill(dp[i] ,-1);
		}
		
		System.out.println(palin(0,n-1));
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(dp[i][j]+" ");	
			}
			System.out.println();
		}
		
	}
	
	static int palin(int s, int e) {
		if (s >= e) return 0;

		if (dp[s][e] != -1) return dp[s][e];

		dp[s][e] = 0; 
		if (arr[s] == arr[e]) { // 값 일치하면 Pass 
//			System.out.println("pass : " + s +", " + e);
			dp[s][e] += palin(s + 1, e - 1);
		} else { // 값 다르면 +1 
//			System.out.println("diff : " + s +", " + e);
			dp[s][e] += 1 + Math.min(palin(s + 1, e), palin(s, e - 1));
		}

		return dp[s][e];
	}

}
