package baekjoon.ttone.dp;

// #2579 dp 계단 오르기 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClimbingStairs {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] data = new int[n+1];
		for(int i=1; i<n+1; i++) {
			data[i]  = Integer.parseInt(br.readLine());
		}
		
		// 1. 한 계단 혹은 두 계단씩 오름
		// 2. 연속된 세 개의 계단 x (시작점 포함 x, 시작점 0)
		// 3. 마지막 계단 필수 
		int[] dp = new int[n+1];
		if(n==1) {
			dp[1] = data[1];
		}else if(n==2) {
			dp[2] = data[2] + data[1];
		}else {
			dp[1] = data[1];
			dp[2] = data[2] + data[1];
			dp[3] = Math.max(data[1] + data[3], data[2] + data[3]);
			
			for(int i=4; i<n+1; i++) {
				// 1. 한 계단 + 한 계단 + (한 칸 뛰고) + 두 계단 오른 경우 
				// 2. 한 번에 두 계단오른 경우
				dp[i] = Math.max(dp[i-3] + data[i] + data[i-1], dp[i-2] + data[i]);
			}
		}
		System.out.println(dp[n]);
	}
}