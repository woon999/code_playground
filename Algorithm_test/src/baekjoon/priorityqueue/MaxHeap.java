package baekjoon.priorityqueue;

// #11279
import java.io.*;
import java.util.*;

public class MaxHeap {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Queue<Integer> q = new PriorityQueue<>();
		
//		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
//		이렇게하면 poll()과 add()에서 -1곱셈을 안해도 된다.
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int k = Integer.parseInt(br.readLine());
			if (k == 0) {
				if (q.isEmpty()) {
					sb.append(0 + "\n");
				} else {
					// 다시 -1을 곱해서 원래 입력받았던 값으로 출력
					sb.append(q.poll() * -1 + "\n");
				}
			} else {
				// 최댓값을 넣어야 하므로 -1을 곱해서 맨앞에 큰숫자가 와야됨 
				q.add(k * -1);
			}
			
//			System.out.println(q);
		}
		System.out.print(sb.toString());
	}
}
