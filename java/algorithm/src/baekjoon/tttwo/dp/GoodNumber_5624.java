package baekjoon.tttwo.dp;

// #5624 dp 좋은 수 

import java.io.*;
import java.util.*;

public class GoodNumber_5624 {

	static final int MOV = 200_000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[400_001];
		int answer = 0;
		// a + b + c = d (a, b, c는 d 이전의 수, 중복 가능) 
		// d - c = a + b 인 (a, b) 구하기 
		for(int i=0; i<n; i++) {
			for(int j=0; j<i; j++) {
				int dMinusC = arr[i] - arr[j]; // -200,000 ~ +200,000
				if(dp[dMinusC + MOV] != 0) {
					answer++;
					break;
				}
			}
			
			for(int j=0; j<=i; j++) {
				dp[arr[i] + arr[j] + MOV] =1;
			}
		}
		
		System.out.println(answer);
	}
}
