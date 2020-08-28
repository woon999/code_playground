package baekjoon.dpandreverseshortestpath;

// #2618
// 중등부 문제 맞아..?
import java.io.*;
import java.util.*;

public class PoliceCar {

	static int n, w;
	static int fmove, fn1, fn2;
	static StringBuilder sb = new StringBuilder();
	static Point[] acc;
	static int[][] dp;
	static int max = 543219876;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		w = Integer.parseInt(br.readLine());

		acc = new Point[w + 1];
		dp = new int[w + 1][w + 1];

		for (int i = 0; i < w + 1; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < w; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			acc[i] = new Point(a, b);
		}

		sb.append(solve(0, 0) + "\n");
		back(0, 0, 0);

		System.out.println(sb.toString());

	}

	static int solve(int x, int y) {

//		System.out.println("x : " + x + ", n2 : "+ y);
		if (x == w || y == w)
			return 0;
		if (dp[x][y] != -1)
			return dp[x][y];

		calc(x, y);
//		
//		for(int i=0; i<dp.length; i++) {
//			for(int j=0; j<dp[i].length; j++) {
//				System.out.print(dp[i][j] +" ");
//			}
//			System.out.println();
//		}
//		
//		System.out.println("---------------");
		return dp[x][y];

	}

	static void back(int num, int x, int y) {
		if (num == w)
			return;
		calc(x, y);

		if (solve(fmove, y) + fn1 >= solve(x, fmove) + fn2) {
			sb.append(2 + "\n");
			back(num + 1, x, fmove);
		} else {
			sb.append(1 + "\n");
			back(num + 1, fmove, y);
		}

	}

	static int dis(Point s, Point e) {
		return Math.abs(s.x - e.x) + Math.abs(s.y - e.y);
	}

	static void calc(int x, int y) {

		int move = Math.max(x, y) + 1;
		int n1 = 0;
		int n2 = 0;
		if (x == 0) {
			n1 = dis(new Point(1, 1), acc[move - 1]); // 1번차(0,0)가 다음 사건까지 걸리는 거리
		} else {
			n1 = dis(acc[x - 1], acc[move - 1]); // 1번차(사건 다녀온 경우 (x,y))가 다음 사건까지 걸리는 거리
		}

		if (y == 0) {
			n2 = dis(new Point(n, n), acc[move - 1]); // 2번차(0,0)가 다음 사건까지 걸리는 거리
		} else {
			n2 = dis(acc[y - 1], acc[move - 1]); // 2번차(사건 다녀온 경우 (x,y))가 다음 사건까지 걸리는 거리
		}

		dp[x][y] = max;
		int result = Math.min(dp[x][y], solve(move, y) + n1);
		dp[x][y] = Math.min(solve(x, move) + n2, result);

//		backtracking할 때 사용 
		fmove = move;
		fn1 = n1;
		fn2 = n2;

	}

}

class Point {
	int x;
	int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}