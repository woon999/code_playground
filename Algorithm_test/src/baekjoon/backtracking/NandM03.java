package baekjoon.backtracking;

import java.io.*;
import java.util.*;

public class NandM03 {

	static int n, m;
	static int[] num;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		num = new int[m];

		dfs(0);
		System.out.println(sb);

	}

	static void dfs(int count) {

		if (count == m) {
			sb.append(num[0]);
			for (int i = 1; i < m; i++) {
				sb.append(" ");
				sb.append(num[i]);
			}
			sb.append("\n");
			return;
		}

		for (int i = 1; i < n + 1; i++) {
			num[count] = i;
			dfs(count + 1);
		}
	}

}
