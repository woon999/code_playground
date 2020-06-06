package baekjoon.stack;

// #10733
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Zero {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		Stack<Integer> s = new Stack<Integer>();
		StringTokenizer st;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());

			if (num == 0) {
				s.pop();
			} else {
				s.push(num);
			}
		}

		int sum = 0;
		for (int i = 0; i < s.size(); i++) {
			sum += s.get(i);

		}

		System.out.println(sum);
	}
}
