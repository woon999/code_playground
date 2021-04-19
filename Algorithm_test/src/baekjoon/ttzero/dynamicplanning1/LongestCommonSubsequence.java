package baekjoon.ttzero.dynamicplanning1;

// #9251
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestCommonSubsequence {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		String str1 = br.readLine();
		String str2 = br.readLine();
		
		System.out.println(LCS(str1,str2));
	}
	
	public static int LCS(String str1, String str2){
		int n1 = str1.length();
		int n2 = str2.length();
		
		int[][] lcs = new int[n1+1][n2+1];
		
		for(int i =1 ; i<=n1; i++){
			for(int j =1; j<=n2; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1] +1;
				}else {
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
				}
			}
		}
//		for(int i =0 ; i<=n1; i++){
//			for(int j =0; j<=n2; j++) {
//				System.out.print(lcs[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return lcs[n1][n2];
				
	}
}
