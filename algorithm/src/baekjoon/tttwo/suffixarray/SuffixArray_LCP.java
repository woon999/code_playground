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
		for(int idx : suffix) {
			System.out.print((idx+1)+" ");
		}
		System.out.println();
		int[] lcp = getLCP(text, suffix);
		System.out.print("x ");
		for(int i=1; i<lcp.length; i++) {
			System.out.print(lcp[i]+" ");
		}
		System.out.println();
	}
	
	static int[] getLCP(String text, List<Integer> suffix){
		int n = suffix.size();
		int[] lcp = new int[n];
		int[] suffixIdx = new int[n];
		
		for(int i=0; i<n; i++) {
			suffixIdx[suffix.get(i)] = i;
		}
		
		int t = 0;
		for(int i=0; i<n; i++) {
			if(suffixIdx[i] == n-1) {
				t = 0;
				continue;
			}
			
			int j = suffix.get(suffixIdx[i]+1);
			
			while(i+t < n && j+t < n) {
				if(text.charAt(i+t) != text.charAt(j+t)) {
					break;
				}
				t++;
			}
			
			lcp[suffixIdx[i]+1] = t;
			
			if(t > 0) {
				t--;
			}
		}
		return lcp;
	}
	
	static List<Integer> getSuffixArray(String text){
		int n  = text.length();
		int t = 1;
		
		List<Integer> tmp = new ArrayList<>();
		int[] group = new int[n+1];
		for(int i=0; i<n; i++) {
			tmp.add(i);
			group[i] = text.charAt(i)-'a';
		}
		group[n] = -1;
		
		CompUsing2T compUsing2T = new CompUsing2T(t, n, group);
		while(t < n) {
			Collections.sort(tmp, compUsing2T.comparator);
			
			t*= 2;
			if(t >= n) break;
			
			int[] nGroup = new int[n+1];
			nGroup[tmp.get(0)] = 0;
			nGroup[n] = -1;
			for(int i=1; i<n; i++) {
				if(compUsing2T.comparator.compare(tmp.get(i-1), tmp.get(i)) < 0 ) {
					nGroup[tmp.get(i)] = nGroup[tmp.get(i-1)] + 1;
				} else {
					nGroup[tmp.get(i)] = nGroup[tmp.get(i-1)];
				}
			}
			
			group = nGroup;
			compUsing2T.changeValues(t, group);
			
		}
		
		return tmp;
	}
}
