package baekjoon.ttone.dataStructure;

// #1655 DataStructure 가운데를 말해요 - Heap, ProirtyQueue  
import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class TellTheMid {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> minHeap = new PriorityQueue<>((o1,o2) -> o2-o1); // 내림차순  
		Queue<Integer> maxHeap = new PriorityQueue<>((o1,o2) -> o1-o2); // 오름차순 
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(minHeap.size() == maxHeap.size()) minHeap.add(num);
			else maxHeap.add(num);
			
			
			// minHeap이 더 클 경우 원소 switch 
			if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				if(minHeap.peek() > maxHeap.peek()) {
					int tmp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(tmp);
				}
			}
			
			sb.append(minHeap.peek()+"\n");
			
			
		}
		
		System.out.println(sb.toString());
		
	}

}
