package baekjoon.ttone.string;

// #1786 String 찾기 - KMP 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Find {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		String p = br.readLine();
		
		int[] table = makeTable(p);
		for(int num : table) {
			System.out.print(num+" ");
		}
		System.out.println();
		
//		KMP(t,p);
		KMP2(t,p);
	}
	
	// KMP 전통적인 구현 
	static void KMP(String parent, String pattern){
		int[] table = makeTable(pattern);
		
		int n1 = parent.length(), n2 = pattern.length();
		int begin =0, matched=0;
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		while(begin <= n1-n2) {
			if(matched < n2 && parent.charAt(begin+matched) == pattern.charAt(matched)) {
				++matched;
				if(matched == n2) {
					sb.append((begin+1)+" ");
					cnt++;
					System.out.println("find" + (begin+1));
				}
			}else{
				if(matched ==0) {
					++begin;
				}else {
					begin += matched - table[matched-1];
					matched = table[matched-1];
				}
			}
		}
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
	
	// KMP 알고리즘 다른 구현 
	// i = begin+matched, idx = matched
	static void KMP2(String parent, String pattern){
		int[] table = makeTable(pattern);
		
		int n1 = parent.length(), n2 = pattern.length();
		int idx=0;
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n1; i++) {
			while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(idx)) {
				if(idx == n2-1) {
					sb.append((i-idx+1)+" ");
					System.out.println("find" + (i-idx+1) + " ~ " + (i+1));
					cnt++;
					idx = table[idx];
				}else {
					idx +=1;
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
		
	}
	
	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		int idx = 0;
		for(int i=1; i<n; i++) {
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
}





