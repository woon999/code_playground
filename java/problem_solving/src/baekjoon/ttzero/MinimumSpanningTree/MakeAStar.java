package baekjoon.ttzero.MinimumSpanningTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


// #4386
public class MakeAStar {

	static int[] parent;
	static ArrayList<Edge2> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		Point[] points = new Point[n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());

			points[i] = new Point(x, y, i);

		}

		list = new ArrayList<Edge2>();

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				double weight = dis(points[i], points[j]);

				list.add(new Edge2(points[i].num, points[j].num, weight));
			}
		}

		Collections.sort(list);

		parent = new int[n];

		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

		double result = 0;
		

		for (int i = 0; i < list.size(); i++) {
			Edge2 e = list.get(i);

			if (find(e.start) != find(e.end)) {
				result += e.weight;
				union(e.start, e.end);
			}
		}

		System.out.println(String.format("%.2f", result));
	}

	static double dis(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}
}

class Point {
	double x;
	double y;
	int num;

	public Point(double x, double y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

class Edge2 implements Comparable<Edge2> {
	int start;
	int end;
	double weight;

	public Edge2(int start, int end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;

	}

	@Override
	public int compareTo(Edge2 o) {
		if (this.weight < o.weight) {
			return -1;
		}

		return 1;
	}
}