package baekjoon.dynamicplanning3;

// #15990
import java.io.*;

public class OneTwoThreePlusFive {

	static int size = 100_001;
	static int mod = 1_000_000_009;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		long res =0;
		long[][] dp = new long[size][4];
		
		dp[1][1] = dp[2][2] = dp[3][3] =1;
		
		for(int i=0; i<dp.length; i++) {
			if( i > 1 ) dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % mod;
			if( i > 2 ) dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % mod;
			if( i > 3 ) dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % mod;
		}
	
		for(int i=0; i<t; i++) {
			int num = Integer.parseInt(br.readLine());
			
			res = (dp[num][1] + dp[num][2] + dp[num][3]) % mod;
			System.out.println(res);
		}
		
	}
}

