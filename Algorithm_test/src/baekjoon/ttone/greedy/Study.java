package baekjoon.ttone.greedy;

//#13904 greedy 과제 - 마감일(d)일 동안 점수를 많이 주는 순대로 풀이하기 

import java.io.*;
import java.util.*;

public class Study {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		List<int[]> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list.add(new int[] {d,w});
		}
		
		Collections.sort(list, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		Queue<Integer> q = new PriorityQueue<>();
		for(int[] p : list) {
			q.add(p[1]);
			if(q.size() > p[0]) {
				q.poll();
			}
		}
		
		int total=0;
		while(!q.isEmpty()) {
			total+= q.poll();
		}
		System.out.println(total);
	}
}
