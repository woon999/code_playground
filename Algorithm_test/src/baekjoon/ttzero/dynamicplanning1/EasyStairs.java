package baekjoon.ttzero.dynamicplanning1;

// #10844

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 1: 1~9  9��
// 2: 10 12 21 23 32 34 43 54 56 65 67 76 78 87 89 98 97 17�� 
public class EasyStairs {

	static int count;
	static long[][] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		num = new long[101][11];
		for(int i =1; i<10; i++) {
			num[1][i] =1;
		}
		
		
		System.out.println(dp(n)% 1000000000);
		
	
	}
	
	static long dp(int n) {
		
		long result=0;
		
		for(int i=2; i<n+1; i++) {
			num[i][0] = num[i-1][1];
			for(int j=1; j<10; j++) {
				num[i][j] = (num[i-1][j-1] + num[i-1][j+1])% 1000000000 ;
			}
			
		}
		
		for(int i=0; i<10; i++) {
			result += num[n][i];
		}
		return result;
	}
}
