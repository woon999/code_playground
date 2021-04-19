package baekjoon.ttzero.greedy;

// #14754
import java.io.*;
import java.util.*;

public class PizzaBoxes {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] arr = new int[n][m];
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		long sum = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				sum += arr[i][j];
			}
		}

		for (int i = 0; i < n; i++) {
			int mx = -1;
			for (int j = 0; j < m; j++) {
				if (mx < arr[i][j]) {
					mx = arr[i][j];
					
				}
			}
			list.add(mx);
		}

		for (int i = 0; i < m; i++) {
			int my = -1;
			for (int j = 0; j < n; j++) {
				if (my < arr[j][i]) {
					my = arr[j][i];
				
				}
			}
			list.add(my);
		}
		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				if (list.contains(arr[i][j])) {
					sum -= (long)arr[i][j];
				}
			}

		}
		System.out.println(sum);

	}
}
