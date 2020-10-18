package baekjoon.greedy;

// #11508
import java.io.*;
import java.util.*;

public class TwoPlueOneSale {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		int sum = 0;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}

		Arrays.sort(arr);

		for (int i = n - 3; i >= 0; i -= 3) {
			sum -= arr[i];
			System.out.println(arr[i] + " ,"+i);
		}
		
		System.out.println(sum);

	}
}
