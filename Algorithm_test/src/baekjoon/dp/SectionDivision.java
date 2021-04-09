package baekjoon.dp;

// #2228 dp 구간 나누기 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SectionDivision {

	static int n,m;
	static int[] sum;
	static int[][] dp;
	static boolean[][] checked;
	static int cnt =0;
	static int Init = Integer.MIN_VALUE/2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		sum = new int[n+1];
		for(int i=1; i<n+1; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(br.readLine());	
		}

		
		// - 32768 ~ 32767
		dp = new int[n+1][m+1];
		checked = new boolean[n+1][m+1];
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], Init);			
		}
		
		System.out.println(solve(n,m));
		
//		for(int i=0; i<n+1; i++) {
//			for(int j=0; j<m+1; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}
		

	}
	
	// top-down 
	static int solve(int idx, int section) {
		System.out.println(idx+", "+ section+ ": " + (++cnt));
		if(section == 0 ) return 0;		
		if(idx < 0 ) return Init;
		
		if(checked[idx][section]) return dp[idx][section];
		
		checked[idx][section] = true;
		dp[idx][section] = solve(idx-1, section);
		for(int i = idx; i>0; i--) {
			dp[idx][section] = Math.max(dp[idx][section], solve(i-2, section-1)+(sum[idx]-sum[i-1]));
		}
		
		return dp[idx][section];
		 
		
	}
}
