package baekjoon.ttone.dp;

// #1932 dp 정수 삼각형 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IntTriangle {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] dp = new int[n+1][n+1];
		StringTokenizer st = null;
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			int x=1;
			while(x<=i && st.hasMoreElements()) {
				int num = Integer.parseInt(st.nextToken());
				
				if(x==1) {
					dp[i][x] = dp[i-1][x] +num;
				}else if(x==i) {
					dp[i][x] = dp[i-1][x-1] +num;
				}else {
					dp[i][x] = Math.max(dp[i-1][x-1], dp[i-1][x])+num;
				}
				x++;
			}
		}
		
		int max =0;
		for(int num : dp[n]) {
			max = Math.max(max, num);
		}
		System.out.println(max);
		
	}
}
