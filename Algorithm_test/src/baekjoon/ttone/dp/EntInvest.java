package baekjoon.ttone.dp;

// #2662 기업투자 (dp, dfs ) 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
//5 3
//1 2 3 4
//2 3 1 2
//3 4 2 5
//4 5 2 3
//5 4 2 1
public class EntInvest {

	static int n,m;
	static int[][] value;
	static int[][] dp;
	static int[][] invested;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		value = new int[n+1][m+1];
		dp = new int[n+1][m+1];
		invested = new int[n+1][m+1];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j=1; j<m+1; j++) {
				value[num][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		for(int i=0; i<n+1;i++) {
//			Arrays.fill(dp[i], -1);
//		}
		
//		bottom-up
		for(int i=1; i<m+1; i++) { // 회사 차례대로 방문 
			for(int j=n; j>0; j--) { // 현재 보고있는 회사에서 총액 n부터 값 채워나가기 
				for(int k=0; k<j+1; k++) {  // 최적값을 찾기 위해  k : 0~j범위로 k를 움직이며 탐색 
					if(dp[j][i] < dp[j-k][i-1]+value[k][i]) {
						
						dp[j][i] = dp[j-k][i-1] + value[k][i];
						System.out.println(i+"번 기업 탐색, 총액 " + j+", 탐색 금액 "  +k+ " = " + dp[j][i]);
						invested[j][i] = j-k;
					}
				}
			}
		}
		
		System.out.println(dp[n][m]);
		out(n,m);
		
		
		
//		top-down
//		for(int i=1; i<n+1; i++) {
//			System.out.print(i+"만원 : ");
//			for(int j=1; j<m+1; j++) {
//				System.out.print(value[i][j]+" ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println(solve(n,1));
//		
//		
		
//		for(int i=1; i<n+1; i++)	{
//			for(int j=1; j<m+1; j++) {
//				System.out.print(dp[i][j]+" ");
//			}	
//			System.out.println();
//		}
//		System.out.println();
//		
//		for(int i=1; i<n+1; i++){
//			for(int j=1; j<m+1; j++) {
//				System.out.print(invested[i][j]+" ");
//			}	
//			System.out.println();
//		}
//		
//		int entNum = n;
//		for(int i=1; i<m+1; i++) {
//			System.out.println(invested[entNum][i]);
//			entNum -= invested[entNum][i];
//		}
		
	}
	
	static void out(int x, int y) {
		if(y==0)	return;
		out(invested[x][y], y-1);
		System.out.print(x-invested[x][y]+" ");
	}
	
	static int solve(int money, int ent) {
		if(ent>m) return 0;
		
		if(dp[money][ent] != -1) return dp[money][ent];
		
//		int res = dp[money][ent];
		dp[money][ent] = 0; 
		for(int i=0; i<money+1; i++) {
			int tmp = value[i][ent] + solve(money-i, ent+1);
			
			// 최대 투자금 저장  
			if(dp[money][ent]<tmp) {
				dp[money][ent] = tmp;
				invested[money][ent] = i;
			}
		}
		
		return  dp[money][ent];
	}
}
