package baekjoon.ttzero.greedy;

// #1461

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Library2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Queue<Integer> pos = new PriorityQueue<>((x, y) -> y - x); //(x, y) -> Integer.compare(y, x) 
		Queue<Integer> neg = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {

			int num = Integer.parseInt(st.nextToken());

			if (num > 0) {
				pos.add(num);
			} else
				neg.add(num);
		}

		int idx;
		int max = 0;
		int sum = 0;

		while (!pos.isEmpty()) {
			for (int i = 0; i < m; i++) {

				if (pos.isEmpty())
					continue;

				idx = Math.abs(pos.poll());

				if (i == 0) {
					sum += idx;
					if (idx > max) {
						max = idx;
					}
				}
			}
		}

		while (!neg.isEmpty()) {

			for (int i = 0; i < m; i++) {

				if (neg.isEmpty())
					continue;

				idx = Math.abs(neg.poll());

				if (i == 0) {
					sum += idx;
					if (idx > max) {
						max = idx;
					}
				}

			}
		}
		
		System.out.println(sum *2 -max);
	}
}
