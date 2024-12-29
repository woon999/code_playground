package baekjoon.tttwo.dp;

// #15489 dp 파스칼 삼각형 

import java.io.*;
import java.util.*;

public class PascalTriangle_15489 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[31][31];
		dp[1][1] = 1;
		for(int i=1; i<=30; i++) {
			for(int j=1; j<=i; j++) {
				if(j==1 || j==i) { // 양 끝 
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
				}
			}
		}
		
		int answer = 0;
		for(int i=0; i<w; i++) {
			for(int j=0; j<=i; j++) {
				answer += dp[r+i][c+j];
			}
		}
		
		System.out.println(answer);
	}
}
