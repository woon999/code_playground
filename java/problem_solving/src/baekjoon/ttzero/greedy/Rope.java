package baekjoon.ttzero.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Rope {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int n = s.nextInt();

		Integer rope[] = new Integer[n];

		for (int i = 0; i < rope.length; i++) {
			rope[i] = s.nextInt();
		}
		System.out.println(max(rope));
		s.close();

	}

	public static int max(Integer arr[]) {

		int n = arr.length;
		int w = 0;
		Arrays.sort(arr, Collections.reverseOrder());
		for (int i = 0; i < n; i++) {
			arr[i] = arr[i] * (i + 1);
			if (w < arr[i])
				w = arr[i];
		}

		return w;

	}
}
