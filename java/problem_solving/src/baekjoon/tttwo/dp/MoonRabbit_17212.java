package baekjoon.tttwo.dp;

// #17212 dp 달나라 토끼를 위한 구매대금 지불 도우미 
import java.io.*;

public class MoonRabbit_17212 {

	static final int[] COINS = {1,2,5,7};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[100_001];
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 2; // 1,2
		dp[4] = 2; // 2,2
		dp[5] = 1;
		dp[6] = 2; // 1,5
		dp[7] = 1;
		for(int i=8; i<=n; i++) {
			int insertCoin = Integer.MAX_VALUE;
			for(int coin : COINS) {
				insertCoin = Math.min(insertCoin, dp[i-coin]);
			}
			dp[i] = insertCoin+1;
		}
		System.out.println(dp[n]);
	}
}
