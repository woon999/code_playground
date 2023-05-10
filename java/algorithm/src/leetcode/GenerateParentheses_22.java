package leetcode;

// #22 - Generate Parentheses

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses_22 {
	public static void main(String[] args) {
		int n = 3;

		System.out.println("generateParenthesis(n) = " + generateParenthesis(n));
	}

	public static List<String> generateParenthesis(int n) {
		List<String> answer = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		make(1, 1, 2*n, sb.append('('), answer);
		return answer;
	}

	static void make(int open, int depth, int limit, StringBuilder sb, List<String> answer) {
		if (open < 0) {
			return;
		}

		if (depth == limit && open == 0) {
			answer.add(sb.toString());
			return;
		}

		if (depth < limit) {
			StringBuilder addBuilder = new StringBuilder(sb);
			addBuilder.append(')');
			make(open - 1, depth + 1, limit, addBuilder, answer);

			addBuilder.deleteCharAt(addBuilder.length()-1);
			addBuilder.append('(');
			make(open + 1, depth + 1, limit, addBuilder, answer);
		}
	}

}
