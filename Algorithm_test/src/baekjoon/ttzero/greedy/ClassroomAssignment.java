package baekjoon.ttzero.greedy;

// #11000
import java.io.*;
import java.util.*;

public class ClassroomAssignment {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][2];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());

		}
		Arrays.sort(arr, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}});
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < n; i++) {
			int e = arr[i][1];

			if (!pq.isEmpty() && pq.peek() <= arr[i][0]) {
				pq.poll();
			}
			pq.offer(e);

		}

		System.out.println(pq.size());
	}

}

//class Schedule implements Comparator<Schedule> {
//	int start;
//	int end;
//
//	public Schedule(int start, int end) {
//		this.start = start;
//		this.end = end;
//	}
//
//	@Override
//	public int compare(Schedule o1, Schedule o2) {
//		if (o1.start == o2.start)
//			return Integer.compare(o1.end, o2.end);
//
//		else 
//			return Integer.compare(o1.start, o2.start);
//		
//	}
//
//}
