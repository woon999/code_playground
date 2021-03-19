package baekjoon.dp;

// #2668 dp 줄어들지 않아 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotDecreaseNumber {
	
	static long[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int t=0; t<tc; t++) {
			int n = Integer.parseInt(br.readLine());
			dp = new long[n+1][10];
			System.out.println(solve(n));
			
			
		}
	}
	
	static long solve(int n) {
		long sum=0;
		for(int i=0; i<n+1; i++) {
			dp[i][0] =1;
		}
		for(int i=0; i<10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i=2; i<n+1; i++) {
			for(int j=1; j<10; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1]; 
			}
		}
		

//		for(int i=1; i<n+1; i++) {
//			for(int j=0; j<10; j++	) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		for(long num : dp[n]) {
			sum +=num;
		}	
		return sum;
	}
}
