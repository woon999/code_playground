package baekjoon.ttone.graph;

// #11657 graph 타임머신 (벨만포드)
import java.io.*;
import java.util.StringTokenizer;

class Bus{
	int u;
	int v;
	int val;
	public Bus(int u,int v, int val) {
		this.u = u;
		this.v = v;
		this.val = val;
	}
}

public class TimeMachine {
	static int n,m;
	static Bus[] e;
	static long[] dist;
	static int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		e = new Bus[m];
		dist = new long[n+1];
		for(int i=1; i<n+1; i++) {
			dist[i] = INF;
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			e[i] = new Bus(u,v,val);
		}
		

		
	
		if(bellmanford(1)) { // 음수 순환 존재하면 -1 출력 
			System.out.println(-1);
		}
		else {
			// 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단거리 출력 
			for(int i=2; i<n+1; i++) {
				if(dist[i] == INF) {// 도달할 수 없으면 -1 
					System.out.println("-1");
				}
				else { // 최단 거리 출력 
					System.out.println(dist[i]);
				}
			}
		}
		
	}
	static boolean bellmanford(int start){
		
		dist[start] = 0;
		
		// n번 반복 
		for(int i=0; i<n; i++) {
			// 매 반복마다 모든 간선을 확인 
			for(int j=0; j<m; j++) {
				int cur = e[j].u;
				int next = e[j].v;
				int cost = e[j].val;
						
				if(dist[e[j].u] == INF) 
					continue;
				// 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우 
				if(dist[next] > (dist[cur] + cost)) {
					dist[next] = dist[cur] + cost;
							
					// n번째 라운드에서 값이 갱신된다면 음수 순환 존재 
					if (i == n-1) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
}