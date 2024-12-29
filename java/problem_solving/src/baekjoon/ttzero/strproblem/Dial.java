package baekjoon.ttzero.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dial {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		int time =0;
		for (int i = 0; i < s.length(); i++) {
			time += (s.charAt(i)-'A')/3 +3;
			if(s.charAt(i)=='S' || s.charAt(i)=='V'
					|| s.charAt(i)=='Y'|| s.charAt(i)=='Z') {
				time--;
			}
		}
		System.out.println(time);
	}
}
