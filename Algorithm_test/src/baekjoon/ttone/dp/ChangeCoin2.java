package baekjoon.ttone.dp;

// #2624 dp 동전 바꿔주기 - 재귀탐색(조합) 
import java.io.*;
import java.util.StringTokenizer;

public class ChangeCoin2 {

	static int t;
	static int k;
	static int[][] coin;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		coin = new int[k][2];
		
		StringTokenizer st = null;
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			
			coin[i][0] = Integer.parseInt(st.nextToken());
			coin[i][1] = Integer.parseInt(st.nextToken());
		}
		dp = new int[k][t+1];
		
		for(int i=0; i<k; i++) {
			for(int j=0; j<t; j++) {
				dp[i][j] = -1;
			}
		}
		
		System.out.println(comb(0, 0));
	}
	
	static int comb(int money, int coinType) {
		if(money == t) {
			return 1;
		}
		if(coinType ==k || money>t) {
			return 0;
		}
		if(dp[coinType][money] !=-1) {
			return dp[coinType][money];
		}
		
		int res = 0;
		for(int i=0; i<coin[coinType][1]+1; i++) {
			int cost = coin[coinType][0]*i;
			res += comb(money + cost , coinType+1 );
		}
		return dp[coinType][money] = res;
	}


}
