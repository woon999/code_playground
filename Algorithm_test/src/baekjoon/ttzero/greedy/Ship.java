package baekjoon.ttzero.greedy;

// #1092
import java.io.*;
import java.util.*;


public class Ship {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int[] crane = new int[n];
		for (int i = 0; i < n; i++) {
			crane[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] box = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}

		int[] check = new int[n];
		Arrays.sort(crane);
		Arrays.sort(box);
		
		
		if (crane[n - 1] < box[m - 1]) {
			System.out.println(-1);
		} else {
			int idx = 0, cnt = 0;

			for (int i = 0; i < m;) {
				if (box[i] <= crane[idx]) {
					check[idx]++;
					i++;
				} else {
					idx++;
				}

			}
		
			while (true) {
				boolean flag = false;
				for (int i = 0; i < n; i++) {
					if (check[i] != 0) {
						check[i]--;
						flag = true;
					} else {
						for (int j = i - 1; j >= 0; j--) {
							if (check[j] != 0) {
								check[j]--;
								flag = true;
								break;
							}
						}
					}
				}

				if (!flag)
					break;
				cnt++;
			}

			System.out.println(cnt);
		}
	}
}
