package suffixarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Suffix Array 멘버-마이어스 알고리즘으로 생성하기
 * while문 내부 시간복잡도 O(nlgn)
 * 배열 lgn번 정렬
 * 전체 시간복잡도 O(n*(lgn)^2)
 */

public class SuffixArray_Manber_Myers {
	public static void main(String[] args) {
		String text = "banana";
		List<Integer> suffix = getSuffixArray(text);

		for(int i : suffix){
			System.out.println(i + " - " + text.substring(i));
		}
	}

	private static List<Integer> getSuffixArray(String text) {
		int n = text.length();
		int t = 1;

		// 접미사 배열이 될 반환 값 (이 동적 배열을 log(n)번 정렬)
		List<Integer> perm = new ArrayList<>();
		// group
		int[] group = new int[2*n];
		Arrays.fill(group, -1);
		for (int i = 0; i < n; i++) {
			perm.add(i);
			// text가 영어 소문자로만 이루어졌다고 가정
			group[i] = text.charAt(i) - 'a';
		}

		CompUsing2T compUsing2T = new CompUsing2T(t , group);
		while (t < n) {
			// group[]은 첫 t글자를 기준으로 계산해뒀다. 첫 2t글자를 기준으로 perm을 다시 정렬한다.
			Collections.sort(perm, compUsing2T.comparator);
			t*= 2;
			if(t >= n) break;

			int[] newGroup = new int[n+1];
			Arrays.fill(newGroup, -1);
			newGroup[perm.get(0)] = 0;

			for(int i=1; i<n; i++){
				if(compUsing2T.comparator.compare(perm.get(i-1), perm.get(i)) < 0){
					newGroup[perm.get(i)] = newGroup[perm.get(i-1)] +1;
				}else{
					newGroup[perm.get(i)] = newGroup[perm.get(i-1)];
				}
			}
			group = newGroup;
			compUsing2T.changeValues(t , group);
		}
		return perm;
	}

	static class CompUsing2T {
		private int t;
		private int[] group;

		public CompUsing2T( int t, int[] group) {
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		public void changeValues(int t, int[] group){
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}

		private Comparator<Integer> comparator = new Comparator<>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (group[o1] != group[o2]) {
					return group[o1] - group[o2];
				}
				return group[o1+t] - group[o2+t];
			}
		};
	}
}

