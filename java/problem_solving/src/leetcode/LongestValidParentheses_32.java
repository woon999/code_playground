package leetcode;

// #32 - Longest Valid Parentheses
import java.util.Stack;

public class LongestValidParentheses_32 {
	public static void main(String[] args) {
		String s = ")()())";

		System.out.println("longestValidParentheses(s) = " + longestValidParentheses(s));
	}

	public static int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int result = 0;
		for(int i = 0; i < s.length(); i++){
			char cur = s.charAt(i);

			if(cur == '('){
				stack.push(i);
			}else{
				if(!stack.isEmpty() && stack.peek() >= 0 && s.charAt(stack.peek()) == '('){
					stack.pop();
					result = Math.max(result, i - stack.peek());
				}else{
					stack.push(i);
				}
			}
		}
		return result;
	}
}
