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
		make(1, 1, 2*n, new StringBuilder(), answer);
		return answer;
	}

	static void make(int open, int depth, int limit, StringBuilder sb, List<String> answer) {
		if (depth == 1) { // start
			sb.append('(');
		}

		if (open < 0) {
			return;
		}

		if (depth == limit && open == 0) {
			System.out.println(sb.toString());
			answer.add(sb.toString());
			return;
		}

		if (depth < limit) {
			StringBuilder left = new StringBuilder(sb);
			left.append(')');
			make(open - 1, depth + 1, limit, left, answer);
		}

		if (depth < limit) {
			StringBuilder right = new StringBuilder(sb);
			right.append('(');
			make(open + 1, depth + 1, limit, right, answer);
		}
	}

}
