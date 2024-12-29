package baekjoon.ttzero.greedy;

import java.util.Scanner;

public class Coin {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int k = s.nextInt();

		int arr[] = new int[n];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.nextInt();
		}

		System.out.println(min(k, arr));
		s.close();

	}

	public static int min(int money, int arr[]) {
		int n = arr.length;
		int count = 0;

		for (int i = (n - 1); i >= 0; i--)
			while (money >= arr[i]) {
				money -= arr[i];
				count++;
			}
		return count;
	}

}
