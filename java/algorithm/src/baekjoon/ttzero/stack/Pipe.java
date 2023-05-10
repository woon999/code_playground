package baekjoon.ttzero.stack;

// #10799
import java.io.*;
import java.util.Stack;

public class Pipe {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> st = new Stack<>();

		String s = br.readLine();
		int count =0;
		
		for(int i=0; i<s.length(); i++) {
			if(s.substring(i, i+1).equals("(")) {
				st.push(i);
			}
			else {
				if(st.peek()==i-1) {
					st.pop();
					count+=st.size();
				}
				else {
					st.pop();
					count++;
				}
			}
		}
		System.out.println(count);
		
		
	}
}
