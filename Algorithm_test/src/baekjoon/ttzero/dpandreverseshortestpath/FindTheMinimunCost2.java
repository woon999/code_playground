package baekjoon.ttzero.dpandreverseshortestpath;

import java.io.*;
import java.util.*;

public class FindTheMinimunCost2 {

	static int n, m;
	static List<Bus>[] list;
	static int[] cos;
	static boolean check[];
	static int[] parent;
	static Stack<Integer> s = new Stack<Integer>();
	static int INF = 567891234;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		list = new ArrayList[n+1];
		cos = new int[n+1];
		check = new boolean[n+1];
		parent = new int[n+1];
		
		Arrays.fill(cos, INF);
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[s].add(new Bus(e,c));

		}
		
		st = new StringTokenizer(br.readLine());
		int src = Integer.parseInt(st.nextToken());
		int dst = Integer.parseInt(st.nextToken());
		
		dijkstra(src);
		
		sb.append(cos[dst]+"\n");
		
		sb.append(trace(src,dst) +"\n");
		
		while(!s.isEmpty()) {
			sb.append(s.pop()+" ");
		}
		
		System.out.println(sb.toString());

	}
	
	static void dijkstra(int src) {
		Queue<Bus> q  = new PriorityQueue<>();
		q.add(new Bus(src, 0));
		cos[src] = 0;
		
		while(!q.isEmpty()) {
			Bus nPos = q.poll();
			int pos = nPos.end;
			
			if(check[pos]) continue;
			check[pos] = true;
			
			for(Bus bus : list[pos]) {
				if(cos[bus.end] > cos[pos] + bus.cost) {
//					System.out.println("bus end :" + bus.end + ", pos : " + pos);
					cos[bus.end] = cos[pos] + bus.cost;
					q.add(new Bus(bus.end, cos[bus.end]));
					
					parent[bus.end] = pos;
				}
			}
			
		}
	}
	
	
	static int trace(int src, int dst) {
		int pos = dst;
		int cnt=0;
		while(pos != src ) {
			s.push(pos);
			cnt++;
			
			pos = parent[pos];
		}
		s.push(pos);
		
		return cnt+1;
	}

}

class Bus implements Comparable<Bus>{
	int end;
	int cost;

	public Bus(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Bus o) {
		return cost - o.cost;
	}
}
