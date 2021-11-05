package baekjoon.ttone.dp;

// #2624 dp 동전 바꿔주기 - 반복문 풀이 
import java.io.*;
import java.util.StringTokenizer;

public class ChangeCoin {

	static int t,k;
	static int[][] coin;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		coin = new int[k+1][2];
		
		StringTokenizer st = null;
		for(int i=1; i<k+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			coin[i][0] = Integer.parseInt(st.nextToken());
			coin[i][1] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[k+1][t+1];
		dp[0][0] = 1;
		for(int i=1; i<k+1; i++) {
			int cost = coin[i][0];
			
			for(int j=0; j<coin[i][1]+1; j++) {
				for(int k=0; k<=t; k++) {
					int pos = k + cost*j;
					if(pos>t) break;
//					System.out.println(cost + " : " +pos);
					dp[i][pos] += dp[i-1][k];
				}
			}
					
		}
	
		
		System.out.println(dp[k][t]);
	}

	
}
