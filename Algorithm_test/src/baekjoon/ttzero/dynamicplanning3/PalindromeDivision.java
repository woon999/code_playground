package baekjoon.ttzero.dynamicplanning3;

// #1509
import java.io.*;

public class PalindromeDivision {
	

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		
		int n = s.length();
		
		s = " " + s; // idx 1부터 시작 
		
		boolean[][] check = new boolean[n+1][n+1];
		for(int i=1; i<n+1; i++) {
			check[i][i] = true;
		}
		
		
		for(int i=1; i<n; i++) {
			if(s.charAt(i) == s.charAt(i+1)) {
				check[i][i+1] = check[i+1][i] = true;
			}
		}
		
		for(int i=2; i<n; i++) {
			for(int j=1; j<n-i+1; j++) {
				if(s.charAt(j) == s.charAt(j+i) && check[j+1][j+i-1]) {
					check[j][j+i] = check[j+i][j] = true;
				}
			}
		}
		
		int[] div = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			for(int j=1; j<i+1; j++) {
				if(check[j][i]) {
					if(div[i] ==0 || div[i] > div[j-1] +1) {
						div[i] = div[j-1] +1;
					}
				} 
			}
		}
		
		System.out.println(div[n]);
	}
}
