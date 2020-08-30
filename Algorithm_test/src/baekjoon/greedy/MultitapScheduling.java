package baekjoon.greedy;

// #1700
import java.io.*;
import java.util.*;

public class MultitapScheduling {

	static int n, k;
	static int[] seq;
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		seq = new int[k];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		solve();

	}

	static void solve() {
		int result = 0;

		for (int i = 0; i < k; i++) {
			int tmp = seq[i];
			if (set.contains(tmp) || isPossible(tmp)) {
				continue;
			}

			int max = -1, idx = -1;
			for (int num : set) {
				int ttmp = 0;
				for (int j = i + 1; j < k; j++) {
					if (num == seq[j])
						break;

					ttmp++;
				}

				if (ttmp > max) {
					idx = num;
					max = ttmp;
				}
			}
			set.remove(idx);
			set.add(seq[i]);
			result++;

		}

		System.out.println(result);
	}

	static boolean isPossible(int x) {
		if (set.size() < n) {
			set.add(x);
			return true;
		}
		return false;
	}

}
