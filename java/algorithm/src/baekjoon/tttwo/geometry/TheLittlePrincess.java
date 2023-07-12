package baekjoon.tttwo.geometry;

// https://www.acmicpc.net/problem/1004

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TheLittlePrincess {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());

		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());

			int result = 0;
			for(int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());
				int cx = Integer.parseInt(st.nextToken());
				int cy = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				if(isIn(x1, y1, cx, cy, r) ^ isIn(x2, y2, cx, cy, r)) {
					result++;
				}
			}

			System.out.println(result);
		}
	}

	private static boolean isIn(int x, int y, int cx, int cy, int r) {
		return Math.pow(x - cx, 2) + Math.pow(y - cy, 2) < Math.pow(r, 2);
	}
}


