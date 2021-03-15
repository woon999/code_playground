package baekjoon.dp;

// #1953 dp LCS3
import java.io.*;

public class LCS3 {

	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		String s3 = br.readLine();
		
		System.out.println(solve(s1,s2,s3));
		
	}
	
	static int solve(String src1, String src2, String src3) {
		dp = new int[src1.length()+1][src2.length()+1][src3.length()+1];
		
		
		for(int i=1; i<src1.length()+1; i++	) {
			for(int j=1; j<src2.length()+1; j++) {
				for(int k=1; k<src3.length()+1; k++) {
					if(src1.charAt(i-1) == src2.charAt(j-1) 
							&& src2.charAt(j-1) == src3.charAt(k-1)) {
						dp[i][j][k] = dp[i-1][j-1][k-1] +1;
					}else {
						dp[i][j][k] = Math.max(dp[i-1][j][k], 
									Math.max(dp[i][j-1][k], dp[i][j][k-1]));
					}
						
				}
			}
		}
		
		return dp[src1.length()][src2.length()][src3.length()];
	}
}
	
	
