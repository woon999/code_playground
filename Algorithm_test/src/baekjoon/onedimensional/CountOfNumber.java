package baekjoon.onedimensional;

import java.io.*;

public class CountOfNumber {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int mul = 1;
		for (int i = 0; i < 3; i++) {
			int a = Integer.parseInt(br.readLine());
			mul *= a;
		}

		int[] check = new int[10];
		
		
		while (mul > 0) {
			check[mul % 10] += 1;
			mul /= 10;
		}
		
		for (int i = 0; i < 10; i++) {
			System.out.println(check[i]);
		}
	}
}
