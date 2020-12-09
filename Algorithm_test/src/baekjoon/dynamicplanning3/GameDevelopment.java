package baekjoon.dynamicplanning3;

// #1516
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GameDevelopment {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		for (int i = 0; i < n + 1; i++) {
			list.add(new ArrayList<>());
		}

		int[] indegree = new int[n + 1];
		int[] value = new int[n + 1];

		for (int i = 1; i < n + 1; i++) {
			st = new StringTokenizer(br.readLine());

			value[i] = Integer.parseInt(st.nextToken());
			while (true) {
				int num = Integer.parseInt(st.nextToken());

				if (num == -1)
					break;

				list.get(num).add(i);
				indegree[i]++;
			}
		}

		topological(list, indegree, value, n);
		
		System.out.println(sb.toString());
	}

	private static void topological(ArrayList<ArrayList<Integer>> list, int[] indegree, int[] value, int n) {
		Queue<Integer> q = new LinkedList<>();

		// 먼저 지어야 할 건물이 없는 건물을 큐에 집어 넣음 
		for (int i = 1; i < n + 1; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		int[] res = new int[n+1]; //건물 짓는데 걸린 시간 
		
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			for(int next : list.get(pos)) {
				indegree[next]--;
				
				// next 건물 짓기 전까지 걸린 시간 
				res[next] = Math.max(res[next], res[pos] + value[pos]);
				
				if(indegree[next] ==0) q.offer(next);
			}
		}
		
		
		for(int i=1; i<n+1; i++) {
			sb.append((res[i] + value[i]) + "\n");
		}

	}
}
