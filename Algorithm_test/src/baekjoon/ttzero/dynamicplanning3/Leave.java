package baekjoon.ttzero.dynamicplanning3;

// #14501
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Leave {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] t = new int[n+10];
		int[] p = new int[n+10];
		int[] dp = new int[n+10];
		
		int max= 0;
		String[] str;
		
		for(int i= 1; i<n+1; i++) {
			str = br.readLine().split(" ");
			t[i] = Integer.parseInt(str[0]);
			p[i] = Integer.parseInt(str[1]);
		}
		
		
		for(int i=1; i<n+2; i++) {
			dp[i] = Math.max(dp[i], max);
			dp[t[i] + i] = Math.max(dp[t[i] + i], p[i] + dp[i]);
			max = Math.max(max , dp[i]);
		}
		
		System.out.println(max);
		
	}
}
