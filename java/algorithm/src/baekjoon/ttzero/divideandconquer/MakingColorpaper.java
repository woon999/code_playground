package baekjoon.ttzero.divideandconquer;

// #2630
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MakingColorpaper {

	static int[][] paper;
	static int white, blue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		paper = new int[n][n];
		white = 0;
		blue = 0;
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		paper(0,0,n);
		System.out.println(white);
		System.out.println(blue);

	}

	static void paper(int x, int y, int n) {
		if (check(x, y, n)) {
			if (paper[y][x] == 0)
				white++;
			else
				blue++;
			return;
		}

		paper(x, y, n / 2);
		paper(x + n / 2, y, n / 2);
		paper(x, y + n / 2, n / 2);
		paper(x + n / 2, y + n / 2, n / 2);
	}

	static boolean check(int x, int y, int n) {
		int divide = paper[y][x];
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (divide != paper[i][j])
					return false;
			}
		}

		return true;
	}
}
