package baekjoon.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class AtmWithSort {
	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		int count;
		int n = s.nextInt();

		int arr[] = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = s.nextInt();
		}

		Arrays.sort(arr);

		count = arr[0];
		for (int i = 1; i < n; i++) {
			arr[i] = arr[i - 1] + arr[i];
			count += arr[i];
		}
		
		System.out.println(count);
		s.close();

	}

}
