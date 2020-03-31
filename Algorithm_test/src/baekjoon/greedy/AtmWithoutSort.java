package baekjoon.greedy;

import java.util.Scanner;

public class AtmWithoutSort {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
	
		int n = s.nextInt();
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			int arrInput = s.nextInt();
			arr[i] = arrInput;
		}

		System.out.println(getCount(arr));
		s.close();
		
	}

	public static int getCount(int[] time) {

		int count;
		int temp;
		int n = time.length;

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (time[i] > time[j]) {
					temp = time[j];
					time[j] = time[i];
					time[i] = temp;
				}
			}
		}

		count = time[0];
		for (int k = 1; k < n; k++) {
			time[k] = time[k - 1] + time[k];
			count += time[k];
		}
		return count;
	}

}
