package baekjoon.dp;

// #13302 dp 리조트 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Resort {

	static int n,m;
	static boolean[] holiday;
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		holiday = new boolean[n+1];
		
		if(m>0) {
			st = new StringTokenizer(br.readLine());

			for(int i=0; i<m; i++) {
				holiday[Integer.parseInt(st.nextToken())] = true;
			}
		}
		
		dp = new int[n+1][n+1];
		for(int i=0; i<n+1; i++) {
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(solve(1, 0));
//		for(int i=0; i<n+1; i++) {
//			for(int j=0; j<3; j++) {
//				System.out.print(dp[i][j]+" ");
//			}
//			System.out.println();
//		}

	}
	
	static int solve(int day, int coupon) {
		
		if(day > n) return 0;
		
		if(dp[day][coupon] != -1) return dp[day][coupon];
		
		dp[day][coupon] = Integer.MAX_VALUE;
		if(holiday[day]) {
			return dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon));	
		}
		else {
		
			if(coupon >= 3 ) {
				dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon-3));
			}
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon)+ 10000);
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day+3, coupon+1) +25000);
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day+5, coupon+2) + 37000);
		
		}
		System.out.println(day+"," +coupon + " :" + dp[day][coupon]);
		
		return dp[day][coupon];
	}
}
