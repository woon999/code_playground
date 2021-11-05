package baekjoon.ttzero.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GroupWordChecker {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int count = n;

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			boolean[] check = new boolean[26];
			for (int j = 0; j < s.length(); j++) {
				if (j > 0) {
					if (check[s.charAt(j) - 'a'] == true && s.charAt(j) != s.charAt(j - 1)) {
						count--;
						break;
					}
				}
				check[s.charAt(j) - 'a'] = true;

			}

		}
		System.out.println(count);
	}

}
