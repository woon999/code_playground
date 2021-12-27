package baekjoon.ttone.networkflow;

// #6086 networkflow 최대유량 - 포드-폴커슨 
import java.io.*;
import java.util.*;

public class MaxFlow {

	static int INF = 987654321, V = 52;
	static int[][] flow, capacity;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		flow = new int[V][V];
		capacity = new int[V][V];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = atoi(st.nextToken().charAt(0));
			int b = atoi(st.nextToken().charAt(0));
			int cost = Integer.parseInt(st.nextToken());
			
//			System.out.println(a+" - " + b + " :" + cost);
			capacity[a][b] += cost;
			capacity[b][a] += cost;
		}
		
		maxFlow(0,25);
	}
	
	static void maxFlow(int src, int sink) {
		int totalFlow = 0;
		int[] parent = new int[V];
		Queue<Integer> q;
		while(true) {
			Arrays.fill(parent, -1);
			q = new LinkedList<>();
			
			parent[src] = src;
			q.add(src);
			System.out.println("#" + src);
			
			while(!q.isEmpty() && parent[sink] == -1) {
				int cur = q.poll();
				System.out.println("##" + cur);
				for(int nxt =0; nxt<V; nxt++) {
					// 잔여 용량이 남아 있는 간선을 따라 탐색한다. 
					if(capacity[cur][nxt] - flow[cur][nxt] > 0 && parent[nxt] == -1) {
						System.out.println(cur+" - " +nxt+ ": ("+capacity[cur][nxt] + " - " + flow[cur][nxt]+")");
						q.add(nxt);
						parent[nxt] = cur;
					}
				}
			}
			
			// 더이상 증가 경로가 없으면 종료 
			if(parent[sink] == -1) break;
			
			// 증가 경로를 통해 유량을 얼마나 보낼지 결정한다. 
			int amount = Integer.MAX_VALUE;
			for(int i=sink; i!=src; i=parent[i]) {
				amount = Math.min(capacity[parent[i]][i] - flow[parent[i]][i], amount);
				System.out.println(parent[i] +" - "+ i +" amount :" + amount);
			}
			
			// 증가 경로를 통해 유량을 보낸다. 
			for(int i=sink; i!=src; i=parent[i]) {
				flow[parent[i]][i] += amount;
				flow[i][parent[i]] -= amount;
				System.out.println("flow " + i +" - " + parent[i] + " : "+ amount);
			}
			
			totalFlow += amount;
			
		}
		System.out.println(totalFlow);
	}
	
	static int atoi(char c) {
		if('A' <= c && c <= 'Z') {
			return c-'A';
		}else if('a' <= c && c <= 'z'){
			return c-'A'-6;
		}
		return -1;
	}
}




