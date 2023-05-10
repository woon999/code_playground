package baekjoon.ttone.dp;

// #9657 dp 돌게임 3
import java.util.Scanner;

public class StoneGame3 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		// if 1 : SK win
		//    0 : CY win  
		int[] dp = new int[n+1];
		dp[1] =1;
		dp[2] =0;
		dp[3] =1;
		dp[4] =1;
		
		for(int i=5; i<n+1; i++) {
			// 1,3,4 == 합 3이면 뭐를 넣어도 다음 사람이 이김 
			if(dp[i-1] + dp[i-3] + dp[i-4] <3) {
				dp[i] =1;
			}else {
				dp[i] =0;
			}
		}
		
		if(dp[n] ==1) {
			System.out.println("SK");
		}else {
			System.out.println("CY");
		}
		
		
		
//		규칙 풀이 
//		int d = n%7;
//		
//		System.out.println(d);
//		
//		switch(d) {
//			case 0: case 2:
//				System.out.println("CY");
//				break;
//			default:
//				System.out.println("SK");
//				break;
//		}
	}
	
}
