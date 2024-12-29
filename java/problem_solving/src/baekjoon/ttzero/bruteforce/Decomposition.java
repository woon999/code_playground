package baekjoon.ttzero.bruteforce;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Decomposition {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		
		System.out.println(constructor(n));

	}

	public static int constructor(int n) {
		int l = String.valueOf(n).length();
		int min = n - (9 * l);
		int result = 0;
		for (int i = min; i < n; i++) {
			int sum = i;
			int j = i;
			while (j > 0) {
				sum += j % 10;
				j/=10;
			}
			if(sum == n) {
				result = i;
				break;
			}
		}
		
		return result;
	}
}
