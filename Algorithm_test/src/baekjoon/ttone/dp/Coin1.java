package baekjoon.ttone.dp;

// #2293 dp 동전1 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Coin1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		for(int i=0; i<n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k+1];
		dp[0] = 1;
		for(int i=0; i<n; i++) {
			int v = coin[i];
			for(int j=1; j<k+1; j++) {
				if(j>=v) {
					dp[j] += dp[j-v];
				}
			}
		}
		
		System.out.println(dp[k]);
	}
}
