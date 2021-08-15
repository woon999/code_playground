package baekjoon.ttone.dp;

// #14002 dp 가장 긴 증가하는 부분 수열 4 - LIS, dp  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class LIS4 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[n];
		dp[0] = 1;
		int lis = 1;
		for(int i=1; i<n; i++) {
			dp[i] = 1;
			for(int j=0; j<i; j++) {
				if(arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					lis = Math.max(lis, dp[i]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(lis+"\n");
		
		Stack<Integer> s = new Stack<>();
		for(int i=n-1; i>=0; i--) {
			if(dp[i] == lis) {
				s.push(arr[i]);
				lis--;
			}
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop()+" ");
		}
		System.out.println(sb.toString());
		
		
	
	}
}

