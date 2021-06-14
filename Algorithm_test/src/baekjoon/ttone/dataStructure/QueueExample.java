package baekjoon.ttone.dataStructure;

// #10845 queue 큐 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class QueueExample {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		Deque<Integer> q = new ArrayDeque<>();
		int n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			String line = st.nextToken();
			
			switch(line) {
			case "push":
				int num = Integer.parseInt(st.nextToken());
				q.add(num);
				break;
			case "pop":
				if(q.isEmpty()) {
					sb.append(-1+"\n");
				}else {
					sb.append(q.poll()+"\n");
				}
				break;
			case "size":
				sb.append(q.size()+"\n");
				break;
			case "empty":
				if(q.isEmpty()) {
					sb.append(1+"\n");
				}else {
					sb.append(0+"\n");
				}
				break;
			case "front":
				if(q.isEmpty()) {
					sb.append(-1+"\n");
				}else {
					sb.append(q.peek()+"\n");
				}
				break;
			case "back":
				int size = q.size();
				if(size==0) {
					sb.append(-1+"\n");
				}else {
					sb.append(q.peekLast()+"\n");
				}
				break;
			}
		}
		
		System.out.println(sb.toString());
	}
}
