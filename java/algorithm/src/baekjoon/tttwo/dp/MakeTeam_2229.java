package baekjoon.tttwo.dp;

// #2229 dp 조 짜기 
import java.io.*;
import java.util.StringTokenizer;

public class MakeTeam_2229 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		// 2 5 7 1 3 4 8 6 9 3
		// (2 5) (7  1) (3 4 8) (9 3) 
		int[] dp = new int[n+1];
		int max, min;
		for(int i=0; i<n; i++) {
			max = min = arr[i];
			for(int j=i; j>=0; j--) { // 0 ~ i: dp[j] + (최대-최소) 최댓값 구하기 
				max = Math.max(max, arr[j]);
				min = Math.min(min, arr[j]);
				
				dp[i+1] = Math.max(dp[i+1], dp[j]+max-min); 
			}
		}
		
		System.out.println(dp[n]);
	
	}
}
