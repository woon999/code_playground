package baekjoon.ttone.dataStructure;

// #1715 dataStructure 카드정렬하기 - Priority Queue  
import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class SortTheCard {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n==1) {
			System.out.println(0);
		}else {
			Queue<Integer> pq = new PriorityQueue<>((o1,o2) -> o1-o2);
			
			for(int i=0; i<n; i++) {
				int num = Integer.parseInt(br.readLine());
				pq.add(num);
			}
			
			int cnt =0;
			while(!pq.isEmpty()) {
				if(pq.size()==1) break;
				
				int a = pq.poll();
				int b=  pq.poll();
				cnt += (a+b);
				pq.add(a+b);
			}
			
			
			System.out.println(cnt);
		}
	}
}
