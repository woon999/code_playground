package baekjoon.ttzero.mathone;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BreakEvenPoint {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int year = Integer.parseInt(st.nextToken());
		int onePrice = Integer.parseInt(st.nextToken());
		int sell = Integer.parseInt(st.nextToken());

		if (sell <= onePrice) {
			System.out.println(-1);
		} else {
			System.out.println(year / (sell - onePrice) + 1);
		}

	}
}
