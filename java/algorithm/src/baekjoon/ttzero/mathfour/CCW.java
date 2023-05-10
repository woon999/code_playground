package baekjoon.ttzero.mathfour;

// #11758
import java.io.*;
import java.util.*;

public class CCW {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Point[] po = new Point[3];
		StringTokenizer st;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			po[i] = new Point(x, y);
		}

		System.out.println(ccw(po));
	}

	public static int ccw(Point[] p) {
		// CCW 공식 (x1y2+x2y3+x3y1)−(y1x2+y2x3+y3x1)
		// CCW는 점 A,B,C를 순서대로 봤을때 반시계방향으로 놓여 있으면 양수를, 시계방향이면 음수를, 평행하게 놓여있으면 0
		int result = ((p[0].x * p[1].y) + (p[1].x * p[2].y) + (p[2].x * p[0].y))
				- ((p[0].y * p[1].x) + (p[1].y * p[2].x) + (p[2].y * p[0].x));
		if (result < 0)
			return -1;
		else if (result > 0)
			return 1;
		else
			return 0;
	}
	

}

class Point {
	int x;
	int y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
