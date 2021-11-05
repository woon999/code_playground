package baekjoon.ttzero.greedy;

// 2847
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		int count = 0;

		
			for (int i = n - 2; i >= 0; i--) {
				if (arr[i] >= arr[i + 1]) {
					count += arr[i] - arr[i + 1] + 1;
					arr[i] = arr[i + 1] - 1;
				}
			}
			System.out.println(count);
		}

	
}
