package baekjoon.ttzero.onedimensional;

import java.io.*;

public class OXQuiz {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		String[] ox = new String[n];

		for (int i = 0; i < n; i++) {
			ox[i] = br.readLine();
		}

		for (int i = 0; i < n; i++) {
			int count = 0;
			int total = 0;
			for (int j = 0; j < ox[i].length(); j++) {
				if (ox[i].charAt(j) == 'O') {
					count++;
					total += count;
				} else {
					count = 0;
				}
			}
			System.out.println(total);
		}
	}
}
