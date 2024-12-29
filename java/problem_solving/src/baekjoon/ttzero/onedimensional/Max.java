package baekjoon.ttzero.onedimensional;

import java.util.*;

public class Max {

	private static int n = 9;
	private static int max = 0;

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int[] arr = new int[n + 1];
		for (int i = 1; i < n + 1; i++) {
			arr[i] = s.nextInt();

		}

		int p = 0;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, arr[i + 1]);
			if (max == arr[i + 1])
				p = i + 1;

		}

		System.out.println(max);
		System.out.println(p);

	}
}
