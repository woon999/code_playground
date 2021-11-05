package baekjoon.ttzero.strproblem;

import java.io.*;
import java.util.StringTokenizer;

public class RepeatString {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String s = st.nextToken();

			for (int j = 0; j < s.length(); j++) {
				for (int k = 0; k < n; k++)
					System.out.print(s.charAt(j));
			}
			System.out.println();

		}
	}
}
