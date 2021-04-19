package baekjoon.ttzero.function;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OneNumber {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		if (n < 100) {
			System.out.println(n);
		} else {
			int result = 99;
			for (int i = 100; i < n + 1; i++) {
				result += oneNum(i);
			}

			System.out.println(result);
		}
	}

	public static int oneNum(int n) {
	
		int num1 = n / 100;
		int num2 = (n % 100) / 10;
		int num3 = n % 100 % 10;

		int count = 0;

		if ((num1 + num3) == 2 * num2)
			count++;
		return count;
	}
}
