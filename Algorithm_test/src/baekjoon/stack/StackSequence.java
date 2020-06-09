package baekjoon.stack;

// #1874
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class StackSequence {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<Integer>();
		ArrayList<Character> al = new ArrayList<Character>();
		int idx = 0;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for(int i =1; i<=n; i++) {
			stack.push(i);
			al.add('+');
			while(!stack.empty() && stack.peek() == arr[idx]) {
				stack.pop();
				idx++;
				al.add('-');
			}

		}

		if(!stack.empty()) {
			System.out.println("NO");
		}else {
			for(int i=0; i<al.size(); i++) {
				System.out.println(al.get(i));
			}
		}
	}
}