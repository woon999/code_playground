package baekjoon.ttzero.mathfour;


// #2162
import java.io.*;
import java.util.StringTokenizer;

public class LineGroup {

	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int n = Integer.parseInt(br.readLine());
		Line[] line = new Line[n + 1];
		arr = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			arr[i] = i;
		}

		long x1, x2, y1, y2;

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());

			x1 = Long.parseLong(st.nextToken());
			y1 = Long.parseLong(st.nextToken());
			x2 = Long.parseLong(st.nextToken());
			y2 = Long.parseLong(st.nextToken());

			line[i] = new Line(x1, y1, x2, y2);

		}

		int in, jn;

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if (i == j)
					continue;

				in = find(i);
				jn = find(j);

				if (in != jn) {
					if (isCrossed(line[i], line[j])) {
						union(i, j);
					}
				}
			}
		}
		int[] count = new int[n + 1];
		for (int i = 1; i < n+1; i++) {
			count[arr[i]]++;
		}
		int max = 0;
		int size = 0;
		for (int i = 1; i < n+1; i++) {
			if (max < count[i])
				max = count[i];
			if (count[i] != 0) {
				size++;
			}
		}

		System.out.println(size);
		System.out.println(max);
	}

	static boolean isCrossed(Line l1, Line l2) {
		long chk1 = ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x1, l2.y1) * ccw(l1.x1, l1.y1, l1.x2, l1.y2, l2.x2, l2.y2);
		long chk2 = ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x1, l1.y1) * ccw(l2.x1, l2.y1, l2.x2, l2.y2, l1.x2, l1.y2);

		if (chk1 == 0 && chk2 == 0) {
			return isOverlapped(l1, l2);
		}
		return chk1 <= 0 && chk2 <= 0;
	}

	static boolean isOverlapped(Line l1, Line l2) {
		if (Math.max(l1.x1, l1.x2) < Math.min(l2.x1, l2.x2))
			return false;
		if (Math.min(l1.x1, l1.x2) > Math.max(l2.x1, l2.x2))
			return false;
		if (Math.max(l1.y1, l1.y2) < Math.min(l2.y1, l2.y2))
			return false;
		if (Math.min(l1.y1, l1.y2) > Math.max(l2.y1, l2.y2))
			return false;
		return true;
	}

	static int ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
		long tmp = x1 * y2 + x2 * y3 + x3 * y1;
		tmp -= (y1 * x2 + y2 * x3 + y3 * x1);

		if (tmp < 0)
			return -1;
		if (tmp > 0)
			return 1;
		return 0;
	}

	static void union(int i, int j) {
		int p = find(i);
		int q = find(j);

		if (p == q)
			return;

		arr[q] = p;
	}

	static int find(int i) {
		if (arr[i] == i)
			return i;
		return arr[i] = find(arr[i]);
	}
}

class Line {
	long x1;
	long y1;
	long x2;
	long y2;

	public Line(long x1, long y1, long x2, long y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
}