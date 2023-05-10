package baekjoon.ttone.graph;

// #1854 graph k번째 최단경로 찾기 - 다익스트라, 최소 
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class KthShortestPath {

	static int n,m,k;
	static List<City>[] list;
	static Queue<Integer>[] dis;
	static class City implements Comparable<City>{
		int t;
		int w;
		
		public City(int t, int w) {
			this.t = t;
			this.w = w;
		}

		@Override
		public int compareTo(City o) {
			return this.w - o.w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dis = new PriorityQueue[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			dis[i] = new PriorityQueue<>(Collections.reverseOrder());// 최소 힙 (내림차순)  
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[f].add(new City(t,w));
		}
		
		solve(1);
		 for (int i = 1; i < n+1; ++i){
            if (dis[i].size() == k) System.out.println(dis[i].peek());
            else System.out.println(-1);
        }
		
	}
	
	static void solve(int start) {
		Queue<City> q = new PriorityQueue<>();
		q.add(new City(start,0));
		dis[start].add(0); //  start -> start 1번째 최단경로 0 

		while(!q.isEmpty()) {
			City pos= q.poll();
			int to = pos.t;
			int weight = pos.w;
			
			for(City nxt : list[to]) {
				//k개 최단경로 저장  
				if(dis[nxt.t].size()<k) {
					dis[nxt.t].add((weight+nxt.w));
					
					q.add(new City(nxt.t, weight+nxt.w));
					
				}
				// dis[nxt.t].size() == k이고 k번째 최단경로보다 현재 경로가 더 작으면 최단경로 갱신  
				else if(dis[nxt.t].peek() > weight+nxt.w) { 
					dis[nxt.t].poll();
					dis[nxt.t].add(weight +nxt.w);
					
					q.add(new City(nxt.t, weight + nxt.w));
				}
			}
		}
	}
}
