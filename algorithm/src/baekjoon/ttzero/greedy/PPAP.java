package baekjoon.ttzero.greedy;

// #16120
import java.io.*;
import java.util.Stack;

public class PPAP {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String text = br.readLine();
		Stack<Character> s = new Stack<>();

		for (int i = 0; i < text.length(); i++) {
			char tmp = text.charAt(i);

			if (tmp == 'P')
				s.push('P');

			else {
				if (s.size() > 1 && i < text.length() - 1 && text.charAt(i + 1) == 'P') {
					s.pop();
					s.pop();
				} else {
					System.out.println("NP");
					return;
				}
			}

		}

		if (s.size() == 1) {
			System.out.println("PPAP");
		} else {
			System.out.println("NP");
		}
	}
}
