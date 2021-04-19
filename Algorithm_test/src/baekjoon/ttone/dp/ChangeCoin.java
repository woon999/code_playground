package baekjoon.ttone.dp;

// #2624 dp 동전 바꿔주기 
import java.io.*;
import java.util.StringTokenizer;

public class ChangeCoin {

	static int t;
	static int k;
	static int[][] coin;
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();
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
		
//		System.out.println(dfs(t, 0));
		
//		for(int i=0; i<k; i++) {
//			for(int j=0; j<t; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
//		System.out.println(sb.toString());
		
		
		// Bottom Up
		int[] bdp= new int[t+1];
		bdp[0] = 1;
		for(int i=0; i<k; i++) {
			int cost = coin[i][0];
			
			for(int j=t; j>= cost; j--) {
				for(int k=1; k<coin[i][1]+1; k++) {
					int pos = j- cost*k;
					if(pos<0) break;
					System.out.println(pos);
					bdp[j] += bdp[pos];
				}
			}
					
		}
		
		
		System.out.println(bdp[t]);
	}

	
//	Top down
	static int dfs(int money, int type ) {
//		System.out.println(t-money);
		if(money == 0) {
			sb.append("coin!");
			sb.append("\n");
			return 1;
		}
		
		if(type== k || money <0 ) {
			return 0;
		}
		
		if(dp[type][t-money] !=-1) return dp[type][t-money];
		
		int cnt=0;
		
		for(int i=0; i<coin[type][1]+1; i++) {
			int cost = coin[type][0] *i;
			sb.append(cost+" "+ i+" ,");
			cnt += dfs(money-cost, type+1);
		}
		
		
		return dp[type][t-money] = cnt;
		
	}
}
