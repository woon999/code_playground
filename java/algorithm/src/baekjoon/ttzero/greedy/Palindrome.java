package baekjoon.ttzero.greedy;

// #17609
import java.io.*;

public class Palindrome {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for(int t=0; t<n; t++) {
			String s = br.readLine();
			
			int result =0;
			int i=0, j= s.length()-1;
			while(i<j) {
				
				if(s.charAt(i) == s.charAt(j)) {
					i++; j--;
				}
				
				else if(s.charAt(i+1) == s.charAt(j) || s.charAt(i) == s.charAt(j-1)) {
					if(s.charAt(i+1) == s.charAt(j) ) {
						int iPos = i+1, jPos = j;
						result = 1;
						
						while(iPos < jPos) {
							if(s.charAt(iPos) == s.charAt(jPos)) {
								iPos++; jPos--;
							}
							else {
								result =2;
								break;
							}
						}
						
					}
					
					if(result != 1 && s.charAt(i) == s.charAt(j-1)) {
						int iPos = i, jPos =j-1;
						result =1;
						
						while(iPos< jPos) {
							if(s.charAt(iPos) == s.charAt(jPos)) {
								iPos++; jPos--;
							}
							else {
								result =2;
								break;
							}
						}
					}
					break;
				}
				
				else {
					result =2;
					break;
				}
			}
			
			System.out.println(result);
		}
	}
}
