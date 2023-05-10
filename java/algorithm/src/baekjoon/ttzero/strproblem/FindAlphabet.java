package baekjoon.ttzero.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class FindAlphabet {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();

		int[] count = new int[26];
		Arrays.fill(count, -1);

		for (int i = 0; i < s.length(); i++) {
			if (count[s.charAt(i) - 'a'] == -1) {
				count[s.charAt(i) - 'a'] = i;
			}
		}
		for (int i = 0; i < count.length; i++) {
			System.out.print(count[i] + " ");
		}
	}

}
