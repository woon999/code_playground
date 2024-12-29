package baekjoon.tttwo.string;

// #6443 str 애너그램 
import java.io.*;
import java.util.*;

public class Anagram {

	static char[] arr;
	static int[] check;
	static StringBuilder sb;
	static Stack<Character> s;
	static Set<String> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder result = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		while(n-- > 0) {
			set = new TreeSet<>();
			arr = br.readLine().toCharArray();
			check = new int[26];
			for(char c : arr) {
				check[c-'a']++;
			}
			s = new Stack<>();
			comb(arr.length);
			set.stream().forEach(s -> result.append(s).append("\n"));
		}
		System.out.println(result.toString());
	}
	
	static void comb(int r) {
		if(r == s.size()) {
			StringBuilder sb = new StringBuilder();
			for(char c : s) {
				sb.append(c);
			}
			set.add(sb.toString());
		}
		
		for(int i=0; i<26; i++){
			if(check[i] > 0) {
				check[i]--;
				s.push((char)(i+'a'));
				comb(r);
				s.pop();
				check[i]++;
			}
		}
	}
}


