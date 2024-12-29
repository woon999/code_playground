package baekjoon.ttzero.greedy;

import java.util.Scanner;

public class NewRecruits {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		int t = s.nextInt();

		for (int i = 0; i < t; i++) {
			int n = s.nextInt();
			int[] x = new int[n];
			for (int j = 0; j < n; j++) {
				x[s.nextInt() - 1] = s.nextInt();
			}
			int count = 1;
			int rating = x[0];
			for (int k = 1; k < n; k++) {
				if (rating > x[k]) {
					count++;
					rating = x[k];
				}
			}

			System.out.println(count);
		}

	}
}

//	public static int fail(int[][] arr) {
//
//		int count = arr.length;
//		int rating = arr[0][1];
//		for (int i = 0; i < arr.length; i++) {
//			if (rating < arr[i][1])
//				count--;
//			rating = arr[i][1];
//		}
//
//		return count;
//
//	}
//
//	public static void arrSort(int[][] arr, int n) {
//		Arrays.sort(arr, new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return Integer.compare(o1[n], o2[n]);
//			}
//		});
//	}
//}
