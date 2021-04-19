package baekjoon.ttzero.dynamicplanning1;

// #9095
import java.io.*;

public class SumOfOneTwoThree {

	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		int[] dp = new int[11];
		dp[1] = 1; 
		dp[2] = 2;
		dp[3] = 4;

		for (int j = 4; j <= 10; j++) { 
			dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1]; 
		}

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());

			System.out.println(dp[n]);
		}

	}
}