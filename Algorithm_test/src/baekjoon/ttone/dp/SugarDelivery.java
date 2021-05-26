package baekjoon.ttone.dp;

// #2839 dp 설탕배달 
import java.io.*;
import java.util.Arrays;

public class SugarDelivery {
	
	static int INF = Integer.MAX_VALUE;
	static int res;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		Arrays.fill(dp,  -1);
		res = INF;
//		solve(n);
		
		System.out.println(solve(n,0) == INF ? -1 : res);
		
	}
	static int solve(int pos, int count) {
		
		if(pos<0) return 0;
		if(dp[pos] != -1) return dp[pos];
		
		if(pos ==0) {
			res = Math.min(res, count);
			return res;
		}
		
		dp[pos] = Math.min(solve(pos-5, count+1), solve(pos-3, count+1));
		
		return res;
		
	}
	
//	static void solve(int n) {
//		
//		while(true) {
//			if(n%5 ==0) {
//				count += n/5;
//				System.out.println(count);
//				return;
//			}else {
//				n -= 3;
//				count++;
//			}
//			
//			if(n<0) {
//				System.out.println(-1);
//				return;
//			}
//	
//		}
//		
//	}
}
