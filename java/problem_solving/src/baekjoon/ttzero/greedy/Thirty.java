package baekjoon.ttzero.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Thirty {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		String num = s.next();
		String[] arr = new String[num.length()];
		int sum = 0;
		for (int i = 0; i < num.length(); i++) {
			sum += (int) num.charAt(i);
			arr[i] = num.charAt(i) + "";
		}

		Arrays.sort(arr, Collections.reverseOrder());
		if (sum % 3 != 0 || !arr[arr.length - 1].equals("0")) {
			System.out.println(-1);
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		
	}

}
