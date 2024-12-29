package baekjoon.ttzero.priorityqueue;

// #1655
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class MiddleHeap {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> big = new PriorityQueue<Integer>((o1,o2)-> o1-o2);
		Queue<Integer> small = new PriorityQueue<Integer>((o1,o2)-> o2-o1);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(big.size() == small.size()) small.offer(num);
			else big.offer(num);
			
			if(!small.isEmpty() && !big.isEmpty()) {
				if(small.peek() > big.peek()) {
					int tmp = big.poll();
					big.offer(small.poll());
					small.offer(tmp);
				}
			}
			System.out.println(small + "," +big);
			sb.append(small.peek()+"\n");
			
			
		}
		System.out.println(sb);
	}
}
