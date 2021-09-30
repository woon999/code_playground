package baekjoon.ttone.greedy;

// #12940 그리디 A와 B 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AandB {

	static int res;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder(br.readLine());
		StringBuilder tb = new StringBuilder(br.readLine());
		
		int flag=0;
		while(tb.length()>sb.length()) {
			if(tb.charAt(tb.length()-1) == 'A') {
				tb.delete(tb.length()-1, tb.length());
			}else {
				tb.delete(tb.length()-1, tb.length());
				tb.reverse();
			}
			
			if(tb.length() == sb.length()) {
				if(tb.toString().equals(sb.toString())) flag =1;
				break;
			}
		}
		System.out.println(flag);
	}
}

// B
// AABBAA
