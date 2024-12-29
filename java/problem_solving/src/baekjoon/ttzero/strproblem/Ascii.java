package baekjoon.ttzero.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Ascii {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		char c = s.charAt(0);
		
		System.out.println((int)c);
	}
}
