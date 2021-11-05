package baekjoon.ttzero.shortestpath;

// #1753
import java.io.*;
import java.util.*;

public class ShortestPath {

	static List<Node>[] list;
	static int[] dis;
	static boolean[] check;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		
		int k = Integer.parseInt(br.readLine());
		
		list = new ArrayList[v+1];
		dis = new int[v+1];
		check = new boolean[v+1];
		
		Arrays.fill(dis, Integer.MAX_VALUE);
		for(int i=1; i<v+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			list[start].add(new Node(end,weight));
		}
		
		StringBuilder sb = new StringBuilder();
		
		dijkstra(k);
		for(int i =1 ;i <v+1; i++) {
			if(dis[i] == Integer.MAX_VALUE) sb.append("INF\n");
			else sb.append(dis[i] +"\n");
		}
		
		System.out.println(sb);
		
		
	}
	
	static void dijkstra(int start) {
		Queue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(start,0));
		dis[start]= 0;
		
		while(!q.isEmpty()) {
			Node nPos = q.poll();
			int pos = nPos.end;

//			for(int i=0; i< dis.length; i++) {
//				System.out.print(dis[i]+" ");
//				
//			}
//			System.out.println("---------------");
			if(check[pos]) continue;
			check[pos] =true;
			
			for(Node node : list[pos]) {
				if(dis[node.end] > dis[pos] + node.weight) {
					dis[node.end] = dis[pos] + node.weight;
					q.add(new Node(node.end, dis[node.end]));
				}
			}
		
		}
	}
}

class Node implements Comparable<Node>{
	int end, weight;

	
	public Node(int end, int weight) {
		this.end =end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}