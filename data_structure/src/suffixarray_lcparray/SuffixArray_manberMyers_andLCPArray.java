package suffixarray_lcparray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Suffix Array 멘버-마이어스 알고리즘으로 생성하기 + LCP 배열 만들기
 * Suffix Array
 * - while문 내부 시간복잡도 O(nlgn)
 * - 배열 lgn번 정렬
 * - 전체 시간복잡도 O(n*(lgn)^2)
 *
 * LCP Array
 * 전체 시간복잡도 O(m + lgn)
 */
public class SuffixArray_manberMyers_andLCPArray {
	public static void main(String[] args) {
		String text = "banana";
		List<Integer> sa = getSuffixArray(text);
		int[] lcp = getLCP(text, sa);
		for (int i : sa) {
			// System.out.println(i + " - " + text.substring(i));
			System.out.print(i + " ");
		}
		System.out.println();

		System.out.print("x ");
		for (int i : lcp) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	private static List<Integer> getSuffixArray(String text) {
		int n = text.length();
		int t = 1;

		// 접미사 배열이 될 반환 값 (이 동적 배열을 log(n)번 정렬)
		List<Integer> sa = new ArrayList<>();
		// group
		int[] group = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sa.add(i);
			// text가 영어 소문자로만 이루어졌다고 가정
			group[i] = text.charAt(i) - 'a';
		}
		group[n] = -1;

		CompUsing2T compUsing2T = new CompUsing2T(n, t, group);
		while (t < n) {
			// group[]은 첫 t글자를 기준으로 계산해뒀다. 첫 2t글자를 기준으로 perm을 다시 정렬한다.
			Collections.sort(sa, compUsing2T.comparator);

			t *= 2;
			if (t >= n)
				break;

			int[] newGroup = new int[n + 1];
			newGroup[sa.get(0)] = 0;
			newGroup[n] = -1;

			for (int i = 1; i < n; i++) {
				if (compUsing2T.comparator.compare(sa.get(i - 1), sa.get(i)) < 0) {
					newGroup[sa.get(i)] = newGroup[sa.get(i - 1)] + 1;
				} else {
					newGroup[sa.get(i)] = newGroup[sa.get(i - 1)];
				}
			}
			group = newGroup;
			compUsing2T.changeValues(t, group);
		}
		return sa;
	}

	static int[] getLCP(String text, List<Integer> sa) {
		int n = sa.size();
		int[] lcp = new int[n - 1];

		// sa의 역함수 배열 isa[sa[i]] = i
		int[] isa = new int[n];

		for (int i = 0; i < n; i++) {
			isa[sa.get(i)] = i;
		}

		int k = 0;
		for (int i = 0; i < n; i++) {
			int idx = isa[i];
			if (idx == n - 1)
				continue;

			int j = sa.get(idx + 1);
			while (i + k < n && j + k < n) {
				if (text.charAt(i + k) != text.charAt(j + k))
					break;
				k++;
			}

			lcp[idx] = k;
			if (k > 0) { // i+1는 최소 k-1
				k--;
			}
		}
		return lcp;
	}

	static class CompUsing2T {
		private int n;
		private int t;
		private int[] group;

		public CompUsing2T(int n, int t, int[] group) {
			this.n = n;
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		public void changeValues(int t, int[] group) {
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		private Comparator<Integer> comparator = new Comparator<>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (group[o1] != group[o2]) {
					return group[o1] - group[o2];
				}
				int left = o1 + t, right = o2 + t;
				if (o1 + t > n) {
					left = n;
				}
				if (o2 + t > n) {
					right = n;
				}
				return group[left] - group[right];
			}
		};
	}
}

