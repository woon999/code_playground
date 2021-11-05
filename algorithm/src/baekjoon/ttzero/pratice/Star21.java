package baekjoon.ttzero.pratice;

import java.io.*;
import java.util.*;

public class Star21 {

	public static void main(String[] args) throws IOException {
//		Scanner s = new Scanner(System.in);
//
//		int n = s.nextInt();
//
//		for (int i = 0; i < 2 * n; i++) {
//
//			if ((i + 1) % 2 == 1) {
//				for (int j = 1; j <= n; j += 2)
//					System.out.print("* ");
//			} else {
//				for (int j = 2; j <= n; j += 2)
//					System.out.print(" *");
//			}
//			System.out.println();
//
//		}
//		s.close();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				sb.append('*');
			} else {
				sb.append(' ');
			}
		}
		sb.append('\n');

		for (int i = 0; i < N; i++) {
			if (i % 2 != 0) {
				sb.append('*');
			} else {
				sb.append(' ');
			}
		}
		sb.append('\n');

		String str = sb.toString();

		for (int i = 1; i < N; i++) {
			sb.append(str);
		}

		System.out.println(sb);
	}
}
