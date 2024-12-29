package baekjoon.ttzero.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BlackJack {
	public static class Scan {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int nextInt() throws Exception {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return Integer.parseInt(st.nextToken());
		}
	}

	public static void main(String[] args) throws Exception {
		Scan sc = new Scan();

		int n = sc.nextInt();
		int m = sc.nextInt();

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			list.add(sc.nextInt());
		}

		Collections.sort(list);

		int max = 0;
		for (int i = 0; i < n - 2; i++) {
			for (int j = i + 1; j < n - 1; j++) {
				for (int k = j + 1; k < n; k++) {
					int sum = list.get(i) + list.get(j) + list.get(k);

					if (sum <= m)
						max = Math.max(max, sum);

				}

			}
		}

		System.out.println(max);

	}
}
