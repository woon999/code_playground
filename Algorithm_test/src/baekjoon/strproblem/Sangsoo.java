package baekjoon.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sangsoo {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");

		int a = 0, b = 0;
		for (int i = 0; i < s.length; i++) {
			String temp = "";
			for (int j = s[i].length() - 1; j >= 0; j--) {
				temp += s[i].charAt(j);
			}
			if (i == 0) {
				a = Integer.parseInt(temp);
			} else
				b = Integer.parseInt(temp);
		}
		if (a > b)
			System.out.println(a);
		else
			System.out.println(b);
	}
}
