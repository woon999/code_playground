package baekjoon.tttwo.suffixarray;

// #9248 suffixarray Suffix Array (+ LCP)   
import java.io.*;
import java.util.*;

public class SuffixArray_LCP {
	static class CompUsing2T{
		private int t;
		private int n;
		private int[] group;
		public CompUsing2T(int t, int n, int[] group) {
			this.t = t;
			this.n = n;
			this.group = group;
		}
		
		public void changeValues(int t, int[] group) {
			this.t = t;
			this.group = Arrays.copyOf(group, group.length);
		}
		
		private Comparator<Integer> comparator= new Comparator<>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(group[o1] != group[o2]) {
					return group[o1] - group[o2]; 
				}
				int left = o1+t, right = o2+t;
				if(o1+t > n) { 
					left = n;
				}
				if(o2+t >  n) {
					right = n;
				}
				return group[left] - group[right];
			}
		};
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String text = br.readLine();
		
		List<Integer> suffix = getSuffixArray(text);
		int[] lcp = getLCP(text, suffix);

		StringBuilder sb = new StringBuilder();
		for(int idx : suffix) {
			sb.append((idx+1) + " ");
		}
		sb.append("\n");
		
		sb.append("x ");
		for(int i=0; i<lcp.length-1; i++) {
			sb.append(lcp[i] + " ");
		}
		System.out.println(sb.toString());
	}
	
	static int[] getLCP(String text, List<Integer> sa){
		int n = sa.size();
		int[] lcp = new int[n];

		// sa의 역함수 배열 isa[sa[i]] = i
		int[] isa = new int[n];

		for(int i=0; i<n; i++) {
			isa[sa.get(i)] = i;
		}
		
		int h = 0;
		for(int i=0; i<n; i++) {
			int k = isa[i];
			if(k == n-1) continue;
			
			int j = sa.get(k+1);
			while(i+h < n && j+h < n) {
				if(text.charAt(i+h) != text.charAt(j+h)) break;
				h++;
			}
			
			lcp[k] = h;
			if(h > 0){
				h--;
			}
		}
		return lcp;
	}
	
	static List<Integer> getSuffixArray(String text){
		int n  = text.length();
		int t = 1;
		
		List<Integer> sa = new ArrayList<>();
		int[] group = new int[n+1];
		for(int i=0; i<n; i++) {
			sa.add(i);
			group[i] = text.charAt(i)-'a';
		}
		group[n] = -1;
		
		CompUsing2T compUsing2T = new CompUsing2T(t, n, group);
		while(t < n) {
			Collections.sort(sa, compUsing2T.comparator);
			
			t*= 2;
			if(t >= n) break;
			
			int[] nGroup = new int[n+1];
			nGroup[sa.get(0)] = 0;
			nGroup[n] = -1;
			for(int i=1; i<n; i++) {
				if(compUsing2T.comparator.compare(sa.get(i-1), sa.get(i)) < 0 ) {
					nGroup[sa.get(i)] = nGroup[sa.get(i-1)] + 1;
				} else {
					nGroup[sa.get(i)] = nGroup[sa.get(i-1)];
				}
			}
			
			group = nGroup;
			compUsing2T.changeValues(t, group);
			
		}
		
		return sa;
	}
}
