package baekjoon.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WomesPresident {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			int k = Integer.parseInt(br.readLine());
			int n = Integer.parseInt(br.readLine());

			System.out.println(num(k, n));

		}

	}

	public static int num(int k, int n) {
		if (n == 0)
			return 0;
		else if (k == 0)
			return n;
		else {
			return num(k - 1, n) + num(k, n - 1);
		}
	}
}
