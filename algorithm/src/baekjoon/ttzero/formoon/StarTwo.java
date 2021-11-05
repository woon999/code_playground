package baekjoon.ttzero.formoon;

import java.io.*;

public class StarTwo {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		for (int i = 1; i < n + 1; i++) {
			for (int j = i; j < n; j++) {
				System.out.print(" ");
			}
			for(int k =n-i; k<n; k++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
