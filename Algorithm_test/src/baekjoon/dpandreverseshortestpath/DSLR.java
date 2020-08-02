package baekjoon.dpandreverseshortestpath;

// #9019
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DSLR {

	static boolean[] check;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			check = new boolean[10000];
			
			bfs(a,b);
			
			sb.append("\n");
			
		}
		System.out.println(sb.toString());
	}

	static void bfs(int a, int b) {
		Queue<Camera> q = new LinkedList<Camera>();
		check[a] = true;
		q.add(new Camera(a, ""));

		while (!q.isEmpty()) {
			Camera c = q.poll();

			if (c.pos == b) {
				sb.append(c.button);
				break;
			}

			int D = (c.pos * 2) % 10000;
			int S = (c.pos == 0) ? 9999 : c.pos - 1;
			int L = (c.pos % 1000) * 10 + c.pos / 1000;
			int R = (c.pos % 10) * 1000 + c.pos / 10;

			if (!check[D]) {
				check[D] =true;
				q.add(new Camera(D,c.button+"D"));
			}
			if (!check[S]) {
				check[S] =true;
				q.add(new Camera(S,c.button+"S"));
			}

			if (!check[L]) {
				check[L] =true;
				q.add(new Camera(L,c.button+"L"));
			}

			if (!check[R]) {
				check[R] =true;
				q.add(new Camera(R,c.button+"R"));
			}

		}

	}

	static class Camera {
		int pos;
		String button;

		public Camera(int pos, String button) {
			this.pos = pos;
			this.button = button;
		}
	}
}
