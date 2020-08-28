package baekjoon.dpandreverseshortestpath;

// #9252
import java.io.*;
import java.util.Stack;

public class LCS2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] s1 = br.readLine().toCharArray();
		char[] s2 = br.readLine().toCharArray();
		
		int len1 = s1.length;
		int len2 = s2.length;
		int[][] dp = new int[len1+1][len2+1];
		
		for(int i=1; i<len1+1; i++) {
			for(int j=1; j<len2+1; j++) {
				if(s1[i-1] != s2[j-1]) {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}else {
					dp[i][j] = dp[i-1][j-1]+1;
				}
			}
		}
		
//		for(int i=0; i<dp.length; i++) {
//			for(int j=0; j<dp[i].length; j++) {
//				System.out.print(dp[i][j]+ " " );
//			}
//			System.out.println();
//		}
		
		sb.append(dp[len1][len2]+" \n");
		
		Stack<Character> s = new Stack<Character>();
		
		int i = len1;
		int j = len2;
		while(i >0 && j>0) {
			if(dp[i][j] == dp[i-1][j]) {
				i--;
			}
			else if(dp[i][j] == dp[i][j-1]) {
				j--;
			}
			else {
				s.push(s1[i-1]);
				i--;
				j--;
			}
		}
		
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		
		System.out.println(sb.toString());
		
		
	}
}
