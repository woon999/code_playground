package baekjoon.ttzero.dynamicplanning1;

// #1003
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class FibonacciFucntion {

	static int[] count = new int[41];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		count[0] = 0;
		count[1] = 1;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			fibocount(t);

			print(t);
		}

	}

	public static int fibocount(int n) {
//		count[n] != 0  �ߺ� ����!
		if (n <= 1 || count[n] != 0)
			return count[n];
		else
			return count[n] = fibocount(n - 1) + fibocount(n - 2);
	}

	static void print(int n) {
		if (n == 0) {
			System.out.println(count[n + 1] + " " + count[n]);
		} else
			System.out.println(count[n - 1] + " " + count[n]);

	}
}
