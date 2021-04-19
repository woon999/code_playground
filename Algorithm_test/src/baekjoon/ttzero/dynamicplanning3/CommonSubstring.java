package baekjoon.ttzero.dynamicplanning3;

// #5582
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommonSubstring {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int res=0;
		int[][] dp = new int[s1.length()+1][s2.length()+1];
		
		for(int i=1; i<s1.length() +1; i++) {
			for(int j=1; j<s2.length() +1; j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] +1;
					
					res = (dp[i][j] > res)? dp[i][j] : res;
				}
			}
		}
		
		
		System.out.println(res);
	}
}
