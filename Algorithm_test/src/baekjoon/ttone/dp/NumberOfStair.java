package baekjoon.ttone.dp;

// #1562 dp 계단수 
import java.util.Scanner;

public class NumberOfStair {
	
	static int MOD = 1_000_000_000; 

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		long[][][] dp = new long[n+1][11][1<<10];
		
		// 1<<i : 0~9 비트마스킹
		for(int i=1; i<10; i++) {
			dp[1][i][1<<i] =1;
		}
		
		/**
		 *  i : i자리 숫자
		 *  j 끝나는 숫자 , k 마킹된 숫자 
			  ex) 	j=9, 10 0000 0000 
					
					k =1, 10 0000 0001
					...
					k = 9, 10 0000 1001 
					...
					k = 1023, 11 1111 1111
					
			i : 2, j : 3, k : 00 0001 1100 : 28 
			2자리 숫자 중 4로 끝나면서 234가 포함된 계단 수는?
		    답 : 23,43  (dp[2][3][12] + dp[2][3][24] = 2개)
		 */
		
		for(int i=2; i<n+1; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<1024; k++) {
					int bit = k | (1 << j);
					if(j==0) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]) % MOD;
					}
					else if(j==9) {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k]) % MOD;
					}
					else {
						dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j+1][k]+ dp[i-1][j-1][k]) % MOD;
					}
				}
			}
		}
		
		long sum = 0;
		System.out.println(dp[2][3][12]);
	
		for(int i=0; i<10; i++) {
			System.out.print(dp[n][i][1023]+" ");
			sum = (sum + dp[n][i][1023]) % MOD;
		}
		System.out.println();
		System.out.println(sum);
	}
	
	
	
	
}
