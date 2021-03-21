package baekjoon.dp;

// #15486 dp 퇴사2 
import java.io.*;
import java.util.StringTokenizer;

public class Retire2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = null; 
		
		int[][] arr =new int[n+1][2];
		int[] dp = new int[n+1];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
//			System.out.println(t + ", " +p);
			arr[i][0] = t;
			arr[i][1] = p;
		}
		
		int max = -1;
		for(int i=0; i<n+1; i++) {
			if(max < dp[i]) {
				max = dp[i];
			}
			
			int nxt = i +arr[i][0];
			if(nxt < n+1) {
				dp[nxt] = Math.max(dp[nxt], max+arr[i][1]);
			}
		}
		
		for(int num : dp) {
			System.out.println(num);
		}
		
		System.out.println(dp[n]);
	}
}
