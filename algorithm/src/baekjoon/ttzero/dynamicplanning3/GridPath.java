package baekjoon.ttzero.dynamicplanning3;

// #10164
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GridPath {

	static int n,m,k;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n+1][m+1];
		
		dp[0][1] = 1;
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<m+1; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		}
		
		System.out.println(solve());
		
	}
	
	static int solve() {
		if(k==0) return dp[n][m];
		
		// k값을 기준으로 구역 나누기   // 3, 5, 8
		int x= k/m + (k%m >0? 1:0);  // 2
		int y = k - (x-1) *m;   	 // 3
		int rx = n -x +1;			 // 2
		int ry = m -y +1;			 // 3
		
		return dp[x][y] * dp[rx][ry];
	}
}
