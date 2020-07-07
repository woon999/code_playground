package baekjoon.priorityqueue;

// #1927
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinHeap {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new PriorityQueue<Integer>();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num ==0) {
				if(!q.isEmpty()) {
				sb.append(q.poll() +"\n");
				}else {
					sb.append(0+"\n");
				}
			}else {
				q.add(num);
			}
//			System.out.println(q);
		}
		System.out.println(sb);
		
		
	}
}
