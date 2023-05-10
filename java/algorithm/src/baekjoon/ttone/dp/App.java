package baekjoon.ttone.dp;

// #7579 dp 앱 - knapsack 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class App {
	static int n,m;
	static int[] appMem, appCost;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		appMem = new int[n];
		appCost = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			appMem[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int allCost =0;
		for(int i=0; i<n; i++) {
			appCost[i] = Integer.parseInt(st.nextToken());
			allCost += appCost[i];
		}
		
		dp = new int[n][allCost+1];
		int result=Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			int mem = appMem[i];
			int cost = appCost[i];
			
			for(int j=0; j<=allCost; j++) {
				if(i==0) {
					if(cost<=j){
						dp[i][j] = mem;
					}
				}
				else {
					if(cost<=j) {
						dp[i][j] = Math.max(dp[i-1][j-cost]+mem, dp[i-1][j]);
					}else {
						dp[i][j] = dp[i-1][j];
					}
				}
				if (dp[i][j] >= m) {
					result = Math.min(result, j);
				}
			}
		}
		
		System.out.println(result);
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<allCost+1; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
	}
	
	
}
