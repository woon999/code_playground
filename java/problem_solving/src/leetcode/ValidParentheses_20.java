package leetcode;

// #20 - Valid Parentheses
import java.util.Stack;

public class ValidParentheses_20 {
	public static void main(String[] args) {
		String s = "()";

		System.out.println("isValid = " + isValid(s));
	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();

		for(int i=0; i<s.length(); i++){
			char c = s.charAt(i);
			if(c == '(' || c == '{' || c == '['){
				stack.push(c);
			}else{
				if(stack.isEmpty()) {
					return false;
				}
				if(c == ')' && stack.peek() == '('){
					stack.pop();
				}else if(c == '}' && stack.peek() == '{'){
					stack.pop();
				}else if(c == ']' && stack.peek() == '['){
					stack.pop();
				}else {
					return false;
				}
			}
		}

		if(!stack.isEmpty()){
			return false;
		}
		return true;
	}
}
