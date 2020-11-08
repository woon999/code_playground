package baekjoon.greedy;

// #1105

import java.io.*;
import java.util.StringTokenizer;

public class Eight {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		String[] L = st.nextToken().split("");
		String[] R = st.nextToken().split("");

		int ans = 0;
		if (L.length == R.length) {
			boolean flag = false;
			for (int i = 0; i < L.length; i++) {
				if (L[i].charAt(0) != R[i].charAt(0)) {
					flag = true;
				}
				if (flag)
					continue;
				if (L[i].charAt(0) == R[i].charAt(0) && L[i].charAt(0) == '8')
					ans++;
			}
		}

		System.out.println(ans);
	}
}
