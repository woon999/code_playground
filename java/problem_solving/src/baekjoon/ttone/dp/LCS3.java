package baekjoon.ttone.dp;

// #1953 dp LCS3
import java.io.*;

public class LCS3 {

	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		
		System.out.println(LCS3(s1,s2,s3));
		
	}
	
	static int LCS3(String str1, String str2, String str3) {
		int n1 = str1.length();
		int n2 = str2.length();
		int n3 = str3.length();
		dp = new int[n1+1][n2+1][n3+1];
		
		for(int i=1; i<n1+1; i++) {
			for(int j=1; j<n2+1; j++) {
				for(int k=1; k<n3+1; k++) {
					if(str1.charAt(i-1) == str2.charAt(j-1) 
							&& str2.charAt(j-1) == str3.charAt(k-1)) {
						dp[i][j][k] = dp[i-1][j-1][k-1] +1;
					}else {
						dp[i][j][k] = Math.max(dp[i-1][j][k],
								Math.max(dp[i][j-1][k], dp[i][j][k-1]));
					}
					
				}
			}
		}
		
		return dp[n1][n2][n3];
	}
}
	
	
