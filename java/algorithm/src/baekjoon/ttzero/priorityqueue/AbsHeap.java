package baekjoon.ttzero.priorityqueue;

// #11286

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class AbsHeap {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new PriorityQueue<Integer>((o1,o2)->{
			int abs1 = Math.abs(o1);
			int abs2 = Math.abs(o2);

			if(abs1 == abs2) return o1 >o2? 1 : -1;
			return abs1 -abs2;
		});
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num ==0) {
				if(!q.isEmpty()) {
					sb.append(q.poll()+"\n");
				}else {
					sb.append(0+"\n");
				}
			}
			else {
				q.add(num);
			}

//			System.out.println(q);
		}
		System.out.println(sb);
		
	}
}
