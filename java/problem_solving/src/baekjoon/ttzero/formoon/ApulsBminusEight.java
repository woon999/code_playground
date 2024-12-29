package baekjoon.ttzero.formoon;

import java.io.*;
import java.util.*;

public class ApulsBminusEight {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());

		for (int i = 1; i < t + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int sum = a + b;

			bw.write("Case #" + i + ": " + a + " + " + b + " = " + sum + "\n");
		}
		bw.flush();

	}
}
