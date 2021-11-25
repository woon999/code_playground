package baekjoon.ttone.string;

// #1701 String Cubeditor - KMP  
import java.io.*;

public class Cubeditor {
	static int max =0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(makeTable(br.readLine()));
	}
	
	static int makeTable(String parent) {
		int n = parent.length();
		for(int pos=0; pos<n; pos++) {
			String pattern = parent.substring(pos);
			int n2 = pattern.length();
			int[] table = new int[n2];
			int idx=0;
			for(int i=1; i<n2; i++) {
				while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
					idx = table[idx-1];
				}
				
				if(pattern.charAt(i) == pattern.charAt(idx)) {
					table[i] = ++idx;
					max = Math.max(max, idx);
				}
			}
		}
		return max;
	}

}

