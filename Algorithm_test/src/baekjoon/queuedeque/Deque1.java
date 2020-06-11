package baekjoon.queuedeque;

// #10866
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Deque1 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> d = new LinkedList<Integer>();
		int n = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			if (s.contains("push")) {
				if (s.contains("front")) {
					d.offerFirst(Integer.parseInt(st.nextToken()));
				} else if (s.contains("back")) {
					d.offer(Integer.parseInt(st.nextToken()));
				}
			}

			else if (s.contains("pop")) {
				if (s.contains("front")) {
					if(d.size()!=0) {
					sb.append(d.poll());
					}
					else {
						sb.append(-1);
					}
					sb.append("\n");
				} else if (s.contains("back")) {
					if(d.size()!=0) {
						sb.append(d.pollLast());
						}
						else {
							sb.append(-1);
						}
					sb.append("\n");
				}
			}
			else if(s.equals("size")){
				sb.append(d.size());
				sb.append("\n");
				
			}
			else if(s.equals("empty")) {
				if(d.size()!=0) {
					sb.append(0);
					}
					else {
						sb.append(1);
					}
				sb.append("\n");
			}
			else if(s.equals("front")) {
				if(d.size()!=0) {
					sb.append(d.peek());
					}
					else {
						sb.append(-1);
					}
				sb.append("\n");
			}
			else if(s.equals("back")) {
				if(d.size()!=0) {
					sb.append(d.peekLast());
					}
					else {
						sb.append(-1);
					}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
}
