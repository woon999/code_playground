package baekjoon.strproblem;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WordStudy {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine().toUpperCase();

		int[] count = new int[26];
		for (int i = 0; i < s.length(); i++) {
			count[s.charAt(i) - 'A']++;

		}

		int max = 0;
		char result = '?';
		for (int i = 0; i < count.length; i++) {
			if (count[i] > max) {
				max = count[i];
				result = (char) (i + 'A');
			} else if (count[i] == max) {
				result = '?';
			}
		}

		System.out.println(result);
	}
}
