package baekjoon.ttzero.dynamicplanning1;

// #9461

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PadoSequence {

	static long[] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		StringTokenizer st;
		memo = new long[101];
		memo[1] = 1;
		memo[2] = 1;
		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			System.out.println(triangle(n));
		}
	}

	static long triangle(int n) {
		if (n <= 2 || memo[n] != 0)
			return memo[n];
		else
			return memo[n] = triangle(n - 2) + triangle(n - 3);

	}
}
