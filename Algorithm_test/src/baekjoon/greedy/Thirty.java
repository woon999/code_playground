package baekjoon.greedy;

import java.util.Scanner;

public class Thirty {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		String str = s.nextLine();
		
		long strLen = str.length();
		int[] numCount = new int[10];
		long total = 0;
		for (int i = 0; i < strLen; i++) {
			int tNum = Integer.parseInt(str.substring(i, i + 1));
			numCount[tNum] += 1;
			total += tNum;
		}

		if (!str.contains("0") || total % 3 != 0) {
			System.out.println("-1");
			return;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 9; i >= 0; i--) {
			while (numCount[i] > 0) {
				sb.append(i);
				numCount[i]--;
			}
		}
		System.out.println(sb.toString());

		s.close();

	}
}
