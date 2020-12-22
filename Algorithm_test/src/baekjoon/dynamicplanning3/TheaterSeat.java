package baekjoon.dynamicplanning3;

// #2302
import java.io.*;

public class TheaterSeat {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[41];
		dp[0] = 1;
		dp[1] = 1;
		dp[2] = 2;
		for(int i=3; i<n+1; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		int m = Integer.parseInt(br.readLine());
		
		int res=1;
		int idx =0;
		int fix =0;
		for(int i=0; i<m; i++) {
			fix = Integer.parseInt(br.readLine());
			res *= dp[fix-idx-1];
			idx = fix;
		}
		
		res *= dp[n-fix];
		System.out.println(res);
		
	}
}
