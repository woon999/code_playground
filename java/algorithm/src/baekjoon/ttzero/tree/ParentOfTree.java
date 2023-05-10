package baekjoon.ttzero.tree;

// #11725
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ParentOfTree {

	static int n;
	static ArrayList<Integer>[] list;
	static int[] parent;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
		parent = new int[n + 1];
		check = new boolean[n + 1];

		for (int i = 1; i < n + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		for (int i = 1; i < n + 1; i++) {
			if (!check[i]) {
				dfs(i);
			}
		}

		for (int i = 2; i < n + 1; i++) {
			System.out.println(parent[i]);
		}

	}

	static void dfs(int num) {
		if (!check[num]) {
			check[num] = true;

			for (int i : list[num]) {
				if (!check[i]) {
					parent[i] = num;
					dfs(i);
				}
			}
		}
	}
}
