package baekjoon.tttwo.dp;

// #15624 dp 피보나치 수 7
import java.io.*;

public class Fibo7 {

	static final int MOD = (int) (1e9+7);
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1000001];
		
		dp[0] = 0;
		dp[1] = 1;
		for(int i=2; i<=n; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%MOD;
		}
		
		System.out.println(dp[n]);
	}
}
