package baekjoon.ttone.dataStructure;

// #2504 dataStructure 괄호의 값 - stack 
import java.io.*;
import java.util.Stack;

public class ValueInParentheses {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();

		Stack<Character> st = new Stack<>();
		boolean flag = true; 
		int answer = 0;
		int cnt =1;
		for(int i=0; i<line.length(); i++) {
			char cur = line.charAt(i);
			if(cur == '(') {
				st.push(cur);
				cnt *= 2;
			}
			else if(cur == '[') {
				st.push(cur);
				cnt *= 3;
			}
			else {
				if(cur == ')') {
					if(st.isEmpty() || st.peek() != '(') {
						flag=false;
						break;
					}
					if(line.charAt(i-1) =='(') {
						answer += cnt;
					}
					st.pop();
					cnt /= 2;
				}else if(cur==']') {
					if(st.isEmpty() || st.peek() != '[') {
						flag=false;
						break;
					}
					if(line.charAt(i-1)=='[') {
						answer += cnt;
					}
					st.pop();
					cnt /= 3;
				}
				else {
					flag = false;
					break;
				}
			}
		}
		if(!flag || !st.isEmpty()) {
			System.out.println(0);
		}else {
			System.out.println(answer);
		}
	}
}
