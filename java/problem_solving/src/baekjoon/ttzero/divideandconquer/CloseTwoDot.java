package baekjoon.ttzero.divideandconquer;

// #2261
import java.io.*;
import java.util.*;

public class CloseTwoDot {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		Point[] pts = new Point[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pts[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(pts, (a, b) -> (a.x - b.x));
		
		TreeSet<Point> set = new TreeSet<>(
				(a, b) -> ((a.y == b.y) ? a.x - b.x : a.y - b.y));
		set.add(pts[0]);
		set.add(pts[1]);
		long result = distance(pts[0], pts[1]);
		int idx = 0;
		for (int i = 2; i < n; i++) {
			Point cp = pts[i];
			
			while (idx < i) {
				Point pt = pts[idx];
				long x = cp.x - pt.x;
				if (x * x > result) {
					set.remove(pt);
					idx += 1;
				} else {
					break;
				}
			}
			int d = (int) Math.sqrt((double) result) + 1;
		
			Point from = new Point(-10001, cp.y - d);
			Point to = new Point(10001, cp.y + d);
			for (Point point : set.subSet(from, to)) {
				long dis = distance(cp, point);
				result = Math.min(result, dis);
			}
			set.add(cp);
		}
	
		bw.write(result + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

	static long distance(Point A, Point B) {
		return (long) ((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
