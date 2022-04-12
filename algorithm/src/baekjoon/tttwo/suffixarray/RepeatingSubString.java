package baekjoon.tttwo.suffixarray;

// #1605 suffixarray 반복 부분문자열 - LCP  
import java.io.*;
import java.util.*;

public class RepeatingSubString {

	static int n;

	static class CompUsingT implements Comparator<Integer> {
		int t;
		int[] group;

		public CompUsingT(int t, int[] group) {
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		public void changeValues(int t, int[] group) {
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		@Override
		public int compare(Integer o1, Integer o2) {
			if (group[o1] != group[o2]) {
				return group[o1] - group[o2];
			}

			int left = o1 + t, right = o2 + t;
			if (left > n) {
				left = n;
			}

			if (right > n) {
				right = n;
			}
			return group[left] - group[right];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		String text = br.readLine();
		
		List<Integer> sa = getSuffixArray(text);
		System.out.println(getLongestSubStrLen(text, sa));
	}

	static List<Integer> getSuffixArray(String text) {
		int t = 1;

		List<Integer> sa = new ArrayList<>();
		int[] group = new int[n + 1];
		for (int i = 0; i < n; i++) {
			sa.add(i);
			group[i] = text.charAt(i) - 'a';
		}
		group[n] = -1;

		CompUsingT comp = new CompUsingT(t, group);
		while (t < n) {
			Collections.sort(sa, comp);

			t *= 2;
			if (t >= n)
				break;

			int[] nGroup = new int[n + 1];
			nGroup[n] = -1;
			for (int i = 1; i < n; i++) {
				if (comp.compare(sa.get(i - 1), sa.get(i)) < 0) {
					nGroup[sa.get(i)] = nGroup[sa.get(i - 1)] + 1;
				} else {
					nGroup[sa.get(i)] = nGroup[sa.get(i - 1)];
				}
			}

			group = nGroup;
			comp.changeValues(t, group);
		}

		return sa;
	}

	static int getLongestSubStrLen(String text, List<Integer> sa) {
		int[] lcp = new int[n - 1];

		int[] isa = new int[n];
		for (int i = 0; i < n; i++) {
			isa[sa.get(i)] = i;
		}
			
		int max = 0;
		int h = 0;
		for (int i = 0; i < n; i++) {
			int k = isa[i];
			if (k == n - 1) continue;

			int j = sa.get(k + 1);
			while (i + h < n && j + h < n) {
				if (text.charAt(i + h) != text.charAt(j + h)) break;

				h++;
			}
			
			lcp[k] = h;
			max = Math.max(max, h);
			if (h > 0) {
				h -= 1;
			}
		}
		return max;
	}
}

