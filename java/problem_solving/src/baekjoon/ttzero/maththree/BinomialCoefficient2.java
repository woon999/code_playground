package baekjoon.ttzero.maththree;

// #11051
import java.util.Scanner;

public class BinomialCoefficient2 {

	static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		dp = new int[n+1][m+1];


		sc.close();
		System.out.println(pascal(n,m));


	}

	static int pascal(int n,int m) {

		if(n ==m || m==0 ) {
			return dp[n][m] = 1;
		}
		
		if(dp[n][m]!=0) return dp[n][m];
		else {
			return dp[n][m] = (pascal(n-1,m-1) + pascal(n-1,m))%10007;
		}
		
	}
}
