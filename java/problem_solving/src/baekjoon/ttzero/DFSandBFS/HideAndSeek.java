package baekjoon.ttzero.DFSandBFS;

// #1697
import java.io.*;
import java.util.*;

public class HideAndSeek {

	static int n, k;
	static int[] pos;
	static int[] dx = { -1, 1, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		pos = new int[100001];
		bfs();

		System.out.println(pos[k]);

	}

	static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		int nPos = n;
		while (!q.isEmpty() && nPos != k) {
			nPos = q.poll();
			for (int i = 0; i < 3; i++) {
				int nx;
				if (i == 2) {
					nx = nPos * dx[i];
				} else {
					nx = nPos + dx[i];
				}
				
				if (nx >= 0 && nx < 100001 && pos[nx] == 0) {
					q.add(nx);
					pos[nx] = pos[nPos] + 1;
				}
			}
		}
	}
}
