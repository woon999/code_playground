package baekjoon.ttzero.dynamicplanning3;


// #13301
import java.io.*;

public class Tile {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		long[] dp = new long[81];
		long[] ddp = new long[81];
 		dp[1] = 1;
		dp[2] = 1;
		
		for(int i=3; i<81; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		ddp[1] = 4;
		for(int i=2; i<81; i++) {
			ddp[i] = ddp[i-1] + dp[i]*2;
		}
		
		System.out.println(ddp[n]);
		
	}
}
