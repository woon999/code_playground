package baekjoon.ttone.dataStructure;

// #7662 dataStructure 이중 우선순위 큐 (최대힙, 최소힙, Map)  
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class DualPriorityQueue {
	
	static Map<Integer, Integer> map;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int test=0; test<t; test++) {
			int input = Integer.parseInt(br.readLine());
			
			Queue<Integer> min = new PriorityQueue<>();
			Queue<Integer> max = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 
			map = new HashMap<>();
			for(int i=0; i<input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();
				
				if(op.equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					max.add(num);
					min.add(num);
					map.put(num, map.getOrDefault(num, 0)+1);
				}else {
					int type = Integer.parseInt(st.nextToken());
					
					if(map.size()==0) continue;
					if(type == 1) { //최댓값 삭제 
						delete(max);
					}else { // 최솟값 삭제
						delete(min);
					}
				}
			}
			
			if (map.size()==0) {
	            sb.append("EMPTY\n");
	        } else {
	        	int res = delete(max);
	        	sb.append(res+" "); // 최댓값 
	        	if(map.size()>0) res = delete(min);
	        	sb.append(res+"\n"); // 최솟값
	        }
		}
		System.out.println(sb.toString());
	}
	
	static int delete(Queue<Integer> q) {
		int res = 0;
		while(true) {
			res = q.poll();
			
			int cnt = map.getOrDefault(res, 0);
			if(cnt ==0) continue;
			
			if(cnt ==1) map.remove(res);
			else map.put(res, cnt-1);
			break;
		}
		
		return res;
	}
}
