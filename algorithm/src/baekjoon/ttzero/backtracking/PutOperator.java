package baekjoon.ttzero.backtracking;

import java.io.*;
import java.util.StringTokenizer;

// #14888
// �Ｚ SW ���� �׽�Ʈ ���� ���� 1

public class PutOperator {

	static int n;
	static int[] num;
	static int[] operator;
	static boolean[] check;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		num = new int[n];
		operator = new int[n - 1];
		check = new boolean[n - 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int index = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			int count = Integer.parseInt(st.nextToken());
			for (int j = 0; j < count; j++) {
				operator[index++] = i + 1;
			}
		}
		
	    dfs(0, 1, num[0], 0);
	    System.out.println(max);
	    System.out.println(min);



	}

	public static void dfs(int v, int index, int x, int length) {
		int result = 0;

		if (length == n - 1) {
			if (x > max) {
				max = x;
			}
			if (x < min) {
				min = x;
			}
		} else {
			for (int i = 0; i < n - 1; i++) {
				if (!check[i]) {
					switch (operator[i]) {
					case 1:
						result = x + num[index];
						break;
					case 2:
						result = x - num[index];
						break;
					case 3:
						result = x * num[index];
						break;
					case 4:
						result = x / num[index];
						break;
					}
					check[i] = true;
					dfs(i, index + 1, result, length + 1);
				}
			}
		}
		// backtracking
		check[v] = false;
	}

}
