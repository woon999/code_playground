package baekjoon.ttzero.stack;

// #9012
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Bracket {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			boolean vps = true;
			Stack<Character> stack = new Stack<Character>();
			
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '(') {
					stack.push(s.charAt(j));
				}
				else if(s.charAt(j)==')') {
					if(!stack.isEmpty()) {
						stack.pop();
					}else {
						vps =false;
						break;
					}
				}

			}
			if(!stack.isEmpty()) {
				vps = false;
			}
			
			if(vps) {
				System.out.println("YES");
			}else {
				System.out.println("NO");
			}
		}
	}
}
