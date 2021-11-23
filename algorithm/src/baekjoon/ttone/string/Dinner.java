package baekjoon.ttone.string;

// #11585 String 속타는 저녁 메뉴 - KMP 
import java.io.*;
import java.util.StringTokenizer;

public class Dinner {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sb.append(st.nextToken());
		}
		String pa = sb.toString();
		
		st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			sb.append(st.nextToken());
		}
		String pattern = sb.toString();
		
		int a = pa.length();
		int b = kmp(pa+pa, pattern);
		int g = gcd(a,b);
		System.out.println((b/g) +"/" + (a/g));
		
	}
	
	static int gcd(int a, int b) {
		while(b>0) {
			int r = a%b; 
			a=b;
			b=r;
		}
		return a;
	}
	
	static int kmp(String parent, String pattern) {
		int n1 = parent.length();
		int n2 = pattern.length();
		int[] table = makeTable(pattern);
		int idx=0;
		int cnt =0;
		for(int i=0; i<n1-1; i++) {
			while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			
			if(parent.charAt(i) == pattern.charAt(idx)) {
				if(n2-1 == idx) {
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
			while(idx>0 && pattern.charAt(i)!= pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
}
