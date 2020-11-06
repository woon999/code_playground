package baekjoon.greedy;


//
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LightAndSwitch {

	static int size, count = 0;
	static boolean[] pos, obj, copy;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		size = Integer.parseInt(br.readLine());
		pos = new boolean[size + 2];
		obj = new boolean[size + 2];

		char[] input = br.readLine().toCharArray();
		for (int i = 1; i < size + 1; i++) {
			if (input[i - 1] == '1') {
				pos[i] = true;
			}
		}

		input = br.readLine().toCharArray();
		for (int i = 1; i < size + 1; i++) {
			if (input[i - 1] == '1') {
				obj[i] = true;
			}
		}

		solve();
	}

	private static void solve() {

		if (isPossible()) {
			System.out.println(count);
			return;
		}

		change(1, false);
		count = 1;

		if (isPossible()) {
			System.out.println(count);
			return;
		}
		System.out.println(-1);
	}

	private static boolean isPossible() {
		copy = pos.clone();

		for (int i = 2; i < size; i++) {
			if (copy[i - 1] != obj[i - 1]) {
				count++;
				change(i, true);
			}
		}

		if (copy[size - 1] != obj[size - 1]) {
			copy[size - 1] = obj[size - 1];

			if (copy[size]) {
				copy[size] = false;
			} else {
				copy[size] = true;
			}

			count++;
		}

		for (int i = 1; i < size + 1; i++) {
			if (copy[i] != obj[i]) {
				return false;
			}
		}

		return true;
	}

	private static void change(int idx, boolean flag) {
		if (flag) {
			for (int i = 0; i < 3; i++) {
				if (copy[idx - 1 + i]) {
					copy[idx - 1 + i] = false;
				} else {
					copy[idx - 1 + i] = true;
				}
			}
		} else {
			for (int i = 0; i < 3; i++) {
				if (pos[idx - 1 + i]) {
					pos[idx - 1 + i] = false;
				} else {
					pos[idx - 1 + i] = true;
				}
			}
		}

	}
}
