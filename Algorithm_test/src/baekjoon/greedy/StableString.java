package baekjoon.greedy;

// #4889
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class StableString {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		Stack<Character> st;
		
		for(int t=1;; t++) {
			char[] str = br.readLine().toCharArray();
			int result =0;
			st = new Stack<>();
			if(str[0] == '-') break;
			
			for(int i =0; i<str.length; i++) {
				if(st.isEmpty() && str[i] == '}') {
					st.add('{');
					result++;
				}else if(str[i] == '}') {
					st.pop();
				}else {
					st.add('{');
				}
			}
			
			result += st.size() /2;
			sb.append(t).append('.').append(' ').append(result).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
