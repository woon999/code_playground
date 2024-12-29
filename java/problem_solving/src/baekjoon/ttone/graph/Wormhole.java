package baekjoon.ttone.graph;

// #1865 graph 웜홀 - 벨만포드 
import java.io.*;
import java.util.*;

public class Wormhole {

	static int n, m, w, INF = 987654321;
	static List<int[]>[] list;
	static int[] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		while(tc-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			list = new ArrayList[n+1];
			dist = new int[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<m+w; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				
				if(i > m-1) {
					list[s].add(new int[] {e,-t});
				}else {
					list[s].add(new int[] {e,t});
					list[e].add(new int[] {s,t});
				}
			}
			
			boolean f = false;
			for(int i=1; i<n+1; i++) {
				if(bellmanford(i)) {
					f = true;
					break;
				}
			}
			
			if(f) {
				sb.append("YES\n");
			}else{
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static boolean bellmanford(int s) {
		Arrays.fill(dist, INF);
		dist[s] = 0;
		
		boolean isUpdated = false;
		for(int i=0; i<n; i++) {
			isUpdated = false;
			for(int j=1; j<n+1; j++) {
				int cur = j;
				for(int[] route : list[j]) {
					int nxt = route[0];
					int cost = route[1];
					
					if(dist[cur] == INF) continue;
					
					if(dist[nxt] > dist[cur] + cost) {
						dist[nxt] = dist[cur] + cost;
						isUpdated = true;
						if(i == n-1) {
							return true;
						}
					}
				}
			}
			
			if(!isUpdated) break;
		}
		return false;
	}
	
	
	static void print() {
		for(int i=1; i<n+1; i++) {
			for(int[] pos : list[i]) {
				System.out.println(i +" - " + pos[0] + " : " + pos[1]);
			}
		}
	}
}
