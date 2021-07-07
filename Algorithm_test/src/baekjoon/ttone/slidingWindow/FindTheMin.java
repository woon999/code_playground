package baekjoon.ttone.slidingWindow;

// #11033 slidingWindow 최솟값 찾기 - deque 
import java.io.*;
import java.util.*;

public class FindTheMin {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		Deque<int[]> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			while(!q.isEmpty() && q.peekLast()[0] > num) q.pollLast();
			
			q.offer(new int[] {num,i});
			if(q.peek()[1] < i -(l-1)) {
				q.poll();
			}
			bw.write(q.peek()[0]+" ");
		}
		
//		시간초과 
//		Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
//			@Override
//			public int compare(int[] o1, int[] o2) {
//				return o1[0] - o2[0];
//			}
//		});
//		st = new StringTokenizer(br.readLine());
//		for(int i=0; i<n; i++) {
//			int num = Integer.parseInt(st.nextToken());
//			q.offer(new int[]{num,i});
//			while((q.peek()[1] < i - (l-1))) {
//				q.poll();
//			}
//			bw.write(q.peek()[0]+" ");
//		}
		
		bw.flush();
		bw.close();
	}
}

