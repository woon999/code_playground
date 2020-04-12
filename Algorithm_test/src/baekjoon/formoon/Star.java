package baekjoon.formoon;

import java.io.*;

public class Star {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 1; i < n + 1; i++) {
			for (int j = n - i; j < n; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
