package baekjoon.ttzero.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NumberOfWord {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		int count = s.length;
		for (int i = 0; i < s.length; i++) {
			if (s[i].isEmpty()) {
				count--;
			}	
		}
		System.out.println(count);
	}
}
