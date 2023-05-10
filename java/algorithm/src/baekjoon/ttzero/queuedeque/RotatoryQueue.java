package baekjoon.ttzero.queuedeque;

// #1021
import java.io.*;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class RotatoryQueue {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Deque<Integer> d = new LinkedList<Integer>();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int result = 0;
		for (int i = 1; i < n + 1; i++) {
			d.offer(i);
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			boolean check = false;

			while (!check) {
				if (d.peek() == num) {
					d.poll();
					check = true;
				}

				else {
					int idx = 0;
					Iterator<Integer> it = d.iterator();
					while (it.hasNext()) {
						if (it.next() == num)
							break;
						idx++;
					}
					if (idx <= d.size() / 2) {
						int a = d.poll();
						d.offer(a);
					} else {
						int a = d.pollLast();
						d.offerFirst(a);
					}
					result++;
				}
			}
		}
		System.out.println(result);

	}
}
