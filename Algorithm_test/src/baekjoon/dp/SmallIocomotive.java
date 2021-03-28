package baekjoon.dp;

// #2616 dp 소형기관차 (누적합) 
import java.io.*;
import java.util.StringTokenizer;

public class SmallIocomotive {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] train = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			train[i] = Integer.parseInt(st.nextToken());
		}
		
		int len = Integer.parseInt(br.readLine());
		int[] sum = new int[n+1];
		sum[0] = 0;
		
		for(int i=0; i<n; i++) {
			sum[i+1] = sum[i] + train[i];
		}
		
		int[][] dp = new int[4][n+1];
		int res = 0;
		for(int i=1; i<4; i++){
			for(int j=1; j<n+1; j++) {
				dp[i][j] = 0;
				if(j >= len) {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-len] + (sum[j] - sum[j-len]));
				}
				res = Math.max(dp[i][j], res);
			}
		}
		
//		for(int i : sum) {
//			System.out.print(i +" ");
//		}
//		System.out.println();
//		
//		for(int i=1; i<4; i++) {
//			for(int j=1; j<n+1; j++) {
//				System.out.print(dp[i][j]+ " ");
//			}
//			System.out.println();
//		}
		
		
		System.out.println(res);
	
	}
}
