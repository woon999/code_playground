package baekjoon.ttone.dp;

// #1003 dp 피보나치 함수 
import java.io.*;

public class FibonacciFunc {

	static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		dp = new int[41];
		dp[0] = 0;
		dp[1] = 1;
		fiboZeroOne(40);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num == 0) {
				sb.append("1 0\n");
			}else {
				sb.append(dp[num-1]+" "+dp[num]+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	static int fiboZeroOne(int n)	{
		if(n==0) return 0;
		else if(dp[n]!= 0) return dp[n];
		else {
			return dp[n] = fiboZeroOne(n-1) + fiboZeroOne(n-2);
		}
	}
}
