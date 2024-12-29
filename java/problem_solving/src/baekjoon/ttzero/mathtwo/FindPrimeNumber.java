package baekjoon.ttzero.mathtwo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FindPrimeNumber {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int count = n;
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			int p = Integer.parseInt(st.nextToken());
			if (p != 1) {
				int t = 2;
				while (t < p) {
					if (p % t == 0) {
						count--;
						break;
					}
					t++;
				}
			} else {
				count--;
			}
		}
		System.out.println(count);
	}
}
