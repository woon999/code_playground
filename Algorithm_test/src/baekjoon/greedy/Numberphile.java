package baekjoon.greedy;
// #1041

//
//보이는 주사위면 1 :5N^2 - 16N + 12
//보이는 주사위면 2 :8N-12
//보이는 주사위면 3 :4
//

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Numberphile {

	static int[][] p3 = { { 0, 1, 2 }, { 0, 2, 4 }, { 0, 3, 4 }, { 0, 1, 3 }, { 1, 2, 5 }, { 1, 3, 5 }, { 2, 4, 5 },
			{ 3, 4, 5 } };
	static int[][] p2 = { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 0, 4 }, { 1, 2 }, { 1, 3 }, { 1, 5 }, { 2, 4 }, { 2, 5 },
			{ 3, 4 }, { 3, 5 }, { 4, 5 } };

	static int m = 4231;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		int[] arr = new int[6];
		for (int i = 0; i < 6; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0, max = 0, min = 200, min2 = 200, min3 = 200;
		for (int i = 0; i < 6; i++) {
			sum += arr[i];

			if (max < arr[i]) {
				max = arr[i];
			}
			if (min > arr[i]) {
				min = arr[i];
			}
		}

		if (n == 1) {
			System.out.println(sum - max);
		}

		else {
			for (int i = 0; i < 8; i++) {
				sum = 0;

				for (int j = 0; j < 3; j++) {
					sum += arr[p3[i][j]];
				}
				if (sum < min3) {
					min3 = sum;
				}
			}
			for (int i = 0; i < 12; i++) {
				sum = 0;

				for (int j = 0; j < 2; j++) {
					sum += arr[p2[i][j]];
				}
				if (sum < min2) {
					min2 = sum;
				}
			}

			System.out.println((long) (4 * min3 + ((long) 8 * n - 12) * min2 + ((long) 5 * n * n - 16 * n + 12) * min));
		}
	}

}
