package baekjoon.ttone.dataStructure;

// #2075 dataStructure  N번째 큰 수 - priority queue 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class NthLargestNum {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new PriorityQueue<>();
		StringTokenizer st = null;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(q.size() == n) {
					if(q.peek() < num) {
						q.poll();
						q.add(num);
					}
				}else {
					q.add(num);
				}
				
			}
		}
		
		System.out.println(q.poll());
	}
}
