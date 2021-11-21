package baekjoon.ttone.string;

// #4354 String 문자열 중복 - KMP
import java.io.*;

public class StringPow {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		String line = null;
		while(!(line = br.readLine()).equals(".")) {
			sb.append(KMP(line+line, line)+"\n");
		}
		System.out.println(sb.toString());
	}
	
	static int KMP(String parent, String pattern) {
		int n1 = parent.length();
		int n2 = pattern.length();
		int[] table = makeTable(pattern);
		int cnt=0;
		int idx=0;
		for(int i=0; i<n1-1; i++) {
			while(idx>0 && parent.charAt(i) != parent.charAt(idx)) {
				idx = table[idx-1];
			}
			if(parent.charAt(i) == pattern.charAt(idx)) {
				if(idx == n2-1) {
					cnt++;
					idx = table[idx];
				}else {
					idx+=1;
				}
			}
		}
		return cnt;
		
	}
	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		int idx=0;
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
