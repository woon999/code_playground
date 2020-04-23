package baekjoon.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StarTen {

	private static String[][] star;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		star = new String[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				star[i][j] = " ";
			}

		}

		star(0, 0, n);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(star[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

	public static void star(int x, int y, int n) {

		if (n == 1) {
			star[x][y] = "*";
			return;
		}

		int a = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
				} else {
					star(x + (a * i), y + (a * j), a);
				}

			}

		}

	}

}
