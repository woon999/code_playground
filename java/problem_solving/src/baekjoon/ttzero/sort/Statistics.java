package baekjoon.ttzero.sort;

import java.io.*;
import java.util.*;

public class Statistics {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		System.out.println(sum(arr));
		System.out.println(mid(arr));
		System.out.println(most(arr));
		System.out.println(range(arr));

	}

	public static int sum(int[] arr) {
		double sum = 0;
		double res = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		res = Math.round(sum / arr.length);

		return (int) Math.ceil(res);
	}

	public static int mid(int[] arr) {

		return arr[arr.length / 2];
	}

	public static int most(int[] arr) {
		int[] check = new int[8001];
		for (int i : arr) {
			check[i + 4000]++;
		}

		int max = Integer.MIN_VALUE;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < check.length; i++) {
			if (check[i] != 0 && check[i] > max) {
				max = check[i];

			}
		}

		for (int i = 0; i < check.length; i++) {
			int x = i;
			if (check[i] == max) {
				list.add(x - 4000);
			}
		}

		Collections.sort(list);
		
		if (list.size() > 1) {
			return list.get(1);
		} else {
			return list.get(0);
		}

	}

	public static int range(int[] arr) {
		return arr[arr.length - 1] - arr[0];
	}

}
