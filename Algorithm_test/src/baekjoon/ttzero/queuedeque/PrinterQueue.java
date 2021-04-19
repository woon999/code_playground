package baekjoon.ttzero.queuedeque;

// #1966
import java.io.*;
import java.util.*;

public class PrinterQueue {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test = Integer.parseInt(br.readLine());

		Queue<Important> q = new LinkedList<Important>();
		StringTokenizer st;
		for (int i = 0; i < test; i++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				q.add(new Important(j, Integer.parseInt(st.nextToken())));
			}

			int result = 1;

			while (!q.isEmpty()) {
				Important cPos = q.poll();
				boolean check = true;

				Iterator<Important> it = q.iterator();
				while (it.hasNext()) {
					Important a = it.next();
					if (cPos.rating < a.rating) {
						check = false;
						break;
					}
				}

				if (check == false) {
					q.offer(cPos);
				} else {
					if (cPos.printNum == m) {
						System.out.println(result);
					} else {
						result++;
					}
				}

			}
		}
	}

	static class Important {
		int printNum;
		int rating;

		public Important(int printNum, int rating) {
			this.printNum = printNum;
			this.rating = rating;
		}
	}
}
