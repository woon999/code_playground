package baekjoon.ttzero.pratice;

import java.util.Scanner;

public class AverageScore {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		int[] arr = new int[5];
		int total = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.nextInt();
			if (arr[i] < 40) {
				arr[i] = 40;
			}
			total += arr[i];
		}

		int average = total / arr.length;

		System.out.println(average);

	}
}
