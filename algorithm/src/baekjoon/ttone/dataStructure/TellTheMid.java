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
		
		Queue<Integer> maxHeap = new PriorityQueue<>((o1,o2) -> o2-o1); // 내림차순  
		Queue<Integer> minHeap = new PriorityQueue<>((o1,o2) -> o1-o2); // 오름차순 
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(maxHeap.size() == minHeap.size()) maxHeap.add(num);
			else minHeap.add(num);
			
			
			// maxHeap이 더 클 경우 원소 switch 
			if(!maxHeap.isEmpty() && !minHeap.isEmpty()) {
				if(maxHeap.peek() > minHeap.peek()) {
					int tmp = maxHeap.poll();
					maxHeap.offer(minHeap.poll());
					minHeap.offer(tmp);
				}
			}
			
			sb.append(maxHeap.peek()+"\n");
			
			
		}
		
		System.out.println(sb.toString());
		
	}

}
