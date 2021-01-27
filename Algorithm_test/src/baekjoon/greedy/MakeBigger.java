package baekjoon.greedy;

// #2812
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class MakeBigger {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		String s = br.readLine();
		
		Stack<Character> stack = new Stack<>();
		
		stack.push(s.charAt(0));
		int cnt = 0;
		boolean flag = true;
		
		for(int i=1; i<s.length(); i++) {
			char ch = s.charAt(i);

			while(!stack.isEmpty() && flag) {
				if(cnt == k) {
					flag = false;
					break;
				}
				if(stack.peek() < ch) {
					stack.pop();
					cnt ++;
				}
				
				else if(stack.peek() >= ch) {
					break;
				}
			
			}
			stack.push(ch);			
		}
		
		if(cnt < k) {
			int gap = k-cnt;
			for(int i=0; i<stack.size()-gap; i++) {
				sb.append(stack.get(i));
			}
		}else {
			for(int i=0; i<stack.size(); i++) {
				sb.append(stack.get(i));
			}
		}
	
		System.out.println(sb.toString());
	}
}
