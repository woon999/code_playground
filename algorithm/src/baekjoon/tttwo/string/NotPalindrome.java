package baekjoon.tttwo.string;

// #15927 str 회문은 회문이 아니야
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NotPalindrome {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s= br.readLine();
		
		boolean f = false;
		int len = s.length();
		for(int i=0; i<len/2; i++) {
			if(s.charAt(i) != s.charAt(len-i-1)) {
				System.out.println(s.length());
				return;
			}else if(s.charAt(i) != s.charAt(i+1)) {
				f = true;
			}
			
		}
		if(f) {
			System.out.println(s.length()-1);
		}else {
			System.out.println(-1);
		}
	}
}
