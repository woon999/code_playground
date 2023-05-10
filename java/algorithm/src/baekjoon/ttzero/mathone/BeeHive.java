package baekjoon.ttzero.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BeeHive {
	// n
	// 1 : 1 1
	// 2 : 2 ~ 7 6 1+(1~6)
	// 3 : 8 ~ 19 12 1+6+(1~12)
	// 4 : 20 ~ 37 18 1+6+12+(1~18)
	// 5 : 38 ~ 61 24 1+6+12+18+(1~24)
	// a(n) = a(n-1)+6(n-1)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(Bee(n));

	}

	public static int Bee(int n) {
		if (n == 1)
			return 1;

		int a = 2;
		int k = 1;
		while (n >= a) {
			a += 6 * k;
			k++;
		}
		return k;
	}
}
