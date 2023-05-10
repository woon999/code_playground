package baekjoon.ttzero.stack;

// #17298
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class RightBigNum {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] a = new int[n];
		for(int i=0; i<n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> s = new Stack<>();
		
		int[] ans = new int[n];
		StringBuilder sb = new StringBuilder();
		for(int i=n; i>0; i--) {
			while(!s.isEmpty() && s.peek() <= a[i-1]) {
				s.pop();
			}
			if(s.isEmpty()) {
				ans[i-1] = -1;
			}
			else {
				ans[i-1] = s.peek();
			}
			s.push(a[i-1]);
		}
		for(int j : ans) {
			sb.append(j+" ");
		}
		System.out.println(sb.toString());
	}
}
