package baekjoon.ttzero.stack;

// #4949
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Bracket2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Stack<Character> stack = new Stack<Character>();		
		
		while (true) {
			stack.clear();
			String s = br.readLine();
			if(s.equals(".")){
				break;
			}
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(' || s.charAt(i) == '[') {
					stack.push(s.charAt(i));
				} else if (s.charAt(i) == ')' || s.charAt(i) == ']') {
					if (stack.isEmpty() || (s.charAt(i) == ']' && stack.peek() == '(')
							|| (s.charAt(i) == ')' && stack.peek() == '[')) {

						stack.push(s.charAt(i));
						break;
					}
					stack.pop();

				}

			}
			if (!stack.isEmpty()) {
				System.out.println("no");
			} else {
				System.out.println("yes");
			}

		}
	}

}
