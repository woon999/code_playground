package baekjoon.mathtwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimeNumber {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int m = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int sum = 0, min = 10001;
		if (m == 1)
			m++;
		for (; m <= n; m++) {
			boolean check = true;
			for (int j = 2; j <= m / 2; j++) {
				if (m % j == 0) {
					check = false;
					break;
				}
			}
			if (check) {
				if (min > m)
					min = m;
				sum += m;
			}
		}
		if (sum == 0)
			System.out.println("-1");
		else {
			System.out.println(sum);
			System.out.println(min);
		}
//		int sum = 0;
//		int min = 10001;
//		if (m==1)
//			m++;
//		for (int i = m; i <= n; i++) {
//			int count = 0;
//			for (int j = 2; j < i; j++) {
//				if (i % j == 0) {
//					count++;
//				}
//			}
//
//			if (count == 0) {
//				sum += i;
//				min = Math.min(min, i);
//			}
//		}
//
//		if (min == 10001) {
//			System.out.println(-1);
//		} else {
//			System.out.println(sum);
//			System.out.println(min);
//		}

	}

}
