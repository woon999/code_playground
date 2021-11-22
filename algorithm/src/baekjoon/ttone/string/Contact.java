package baekjoon.ttone.string;

// #1013 String Contact - 정규식 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contact {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(t-->0) {
			String line = br.readLine();
//			sb.append(patternMatching(line) ? "YES\n" : "NO\n");
			sb.append(solve(line) ? "YES\n" : "NO\n");
		}
		System.out.println(sb.toString());
	}
	
	static boolean patternMatching(String parent) {
		return parent.matches("(100+1+|01+)+");
	}
	
	static boolean solve(String parent) {
		if(parent.startsWith("01")) {
			if(parent.length()==2) return true;
			if(solve(parent.substring(2,parent.length()))) return true;
		}
		else if(parent.matches("100+1+[01]*")) {
			int s = parent.indexOf('1', 1);
			int e = s;
			while(parent.charAt(e)=='1') {
				if(parent.length()==e+1) return true;
				e++;
			}
			for(int i=s; i<e; i++) {
				if(solve(parent.substring(i+1,parent.length()))) return true;
			}
		}
		return false;
	}
}


