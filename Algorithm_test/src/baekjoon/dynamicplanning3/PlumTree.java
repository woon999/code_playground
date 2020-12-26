package baekjoon.dynamicplanning3;

// #2240
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PlumTree {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		int[] plum = new int[t+1];
		for(int i=1; i<t+1; i++) {
			plum[i] = Integer.parseInt(br.readLine());
		}
		int[][] dp = new int[w+1][t+1];
		
		for(int i=1; i<t+1; i++) {
			dp[0][i] = dp[0][i-1];
			
			if(plum[i]==1) {
				dp[0][i]++;
			}
		}
		
		int pos =0;
		for(int i=1; i<w+1; i++) {
			if(i%2 ==0) pos=1;
			else pos=2;
			
			for(int j=1; j<t+1; j++) {
				if(plum[j]==pos) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]+1);
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		System.out.println(dp[w][t]);
	}
}
