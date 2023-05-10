package baekjoon.ttone.dp;

// #1463 dp 1로 만들기 
import java.io.*;

public class MakeOne {
	
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws  IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		toOne(n,0);
		System.out.println(ans);
//		System.out.println(bottomUp(n));
	}
	
	static int bottomUp(int num) {
		int[] dp = new int[num+1];
		
		for(int i=2; i<num+1; i++) {
			dp[i] = dp[i-1]+1; // -1 
			if(i%2==0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
			if(i%3==0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
			
		}
		return dp[num];
	}
	
	static void toOne(int num, int cnt) {
		if(num==1) {
			ans = Math.min(ans, cnt);
			return;
		}
		
		if(cnt >= ans) return;
		
		if(num%3==0) {
			toOne(num/3, cnt+1);
		}
		if(num%2 ==0) {
			toOne(num/2, cnt+1);
		}
		toOne(num-1, cnt+1);
	}
	
}
