package baekjoon.dp;

// #1788 dp 피보나치 수의 확장  
import java.io.*;
import java.util.Arrays;

public class ExtendedFibonacci {

	static int[] dp;
	static final int mod = 1_000_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		
		if(n>0) {
			System.out.println(1);
			
		}else if (n<0) {
			n = -n;
			if(n%2 ==0) {
				System.out.println(-1);
			}else {
				System.out.println(1);
			}
			
		}else {
			System.out.println(0);
			System.out.println(0);
			return;
		}
		
//		dp = new int[n+1];
//		System.out.println(fibo(n));
		
		//  bottom-up
		dp = new int[1_000_001];
		dp[1] = 1;
		dp[2] = 1;
		for(int i=3; i<n+1; i++) {
			dp[i] = (dp[i-1] + dp[i-2])%mod;
		}
	
		System.out.println(dp[n]);
		
		
	}
	
	// top-down
	static int fibo(int x) {
		if(x ==1 || x==2) return 1;
		if(dp[x] != 0) return dp[x];
		return dp[x] = (fibo(x-1)+ fibo(x-2))%mod;
		
	}
}
