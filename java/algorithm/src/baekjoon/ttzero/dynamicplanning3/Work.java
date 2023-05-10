package baekjoon.ttzero.dynamicplanning3;

// #2056
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Work {
	
	static int n;
	static int[] times;
	static int[] indegree;
	static List<Integer>[] list;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		
		times = new int[n+1];
		indegree = new int[n+1];
		
		list = new ArrayList[n+1];
		
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=1; i<n+1; i++) {
			st = new StringTokenizer(br.readLine());
			
			times[i] = Integer.parseInt(st.nextToken());
			int work = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<work; j++) {
				int required = Integer.parseInt(st.nextToken());
				list[required].add(i);
				indegree[i]++;
			}
		}
		
		topologySort();
		
	}
	
	public static void topologySort() {
		
		int[] res = new int[n+1];
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<n+1; i++) {
			if(indegree[i] ==0) {
				q.offer(i);
				res[i] = times[i];
			}
		}
		
		for(int i=1; i<n+1; i++) {
			if(q.isEmpty()) {
				System.out.println("cycle");
				return;
			}
			
			int node = q.poll();
			for(int j=0; j<list[node].size(); j++) {
				int adj = list[node].get(j);
				
				if(res[adj] < times[adj] + res[node]){
					res[adj] = times[adj] + res[node];
				}
				
				indegree[adj]--;
				if(indegree[adj] == 0) {
					q.offer(adj);
				}
			}
		}
		
		Arrays.sort(res);
		System.out.println(res[n]);
	}
	
}
