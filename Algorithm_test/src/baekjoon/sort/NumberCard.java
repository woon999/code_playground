package baekjoon.sort;


// #10815
import java.io.*;
import java.util.*;

public class NumberCard {

	static int n, m;
	static int arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());

			if(binarySearch(num)) System.out.print("1 ");
			else System.out.print("0 ");
		}
	}

	static boolean binarySearch(int num) {
		int left = 0;
		int right = n - 1;

		while (left <= right) {
			int midIdx = (left + right) / 2;
			int mid = arr[midIdx];

			if (num < mid)
				right = midIdx - 1;
			else if (num > mid)
				left = midIdx + 1;
			else
				return true;
		}

		return false;

	}
}
