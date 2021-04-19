package baekjoon.ttzero.queuedeque;

// #18258
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Queue2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList<Integer>();
		
		
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		int idx = 0;
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (s.contains("push")) {
				int a = Integer.parseInt(st.nextToken());
				queue.offer(a);
				arr[idx] =a;
				idx++;
			} else if (s.equals("pop")) {
				if (!queue.isEmpty()) {
					sb.append(queue.poll());
				} else {
					sb.append(-1);
				}
				sb.append("\n");
				
			} else if (s.equals("size")) {
				sb.append(queue.size());
				sb.append("\n");
			} else if (s.equals("empty")) {
				if (!queue.isEmpty()) {
					sb.append(0);
				} else {
					sb.append(1);
				}
				
				sb.append("\n");
				
			} else if (s.equals("front")) {
				if (!queue.isEmpty()) {
					sb.append(queue.peek());
				} else {
					sb.append(-1);
				}
				sb.append("\n");
				
				
			} else if (s.equals("back")) {
				if (!queue.isEmpty()) {
					sb.append(arr[idx-1]);

				} else {
					sb.append(-1);
				}
				sb.append("\n");
			}
			
			
		
		}
		
		System.out.println(sb);
	}
}
