package baekjoon.ttzero.mathfour;

// #2166
import java.io.*;
import java.util.*;

public class PolygonWidth {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		Dot[] spot = new Dot[N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			spot[i] = new Dot(x, y);
		}

		long ans = 0;
		for (int i = 2; i < N; i++) {
			ans += area(spot[1], spot[i], spot[i + 1]);
		}
		ans = Math.abs(ans);
		if (ans % 2 == 1)
			System.out.println((ans / 2) + ".5");
		else
			System.out.println((ans / 2) + ".0");

		
	}

	public static long area(Dot first, Dot second, Dot third) {
		return ((first.x * second.y + second.x * third.y + third.x * first.y)
				- (first.x * third.y + second.x * first.y + third.x * second.y));
	}
}

class Dot {
	long x, y;

	public Dot(long x, long y) {
		this.x = x;
		this.y = y;
	}
}