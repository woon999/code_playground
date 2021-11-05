package baekjoon.ttzero.DFSandBFS;

// #1707 bfs/dfs 이분 그래프 
import java.io.*;
import java.util.*;

public class BinaryGraph {

	static int[] color;
	static List[] list;
	static int v;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int t=0; t<n; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			list = new ArrayList[v+1];
			for(int i=0; i<v+1; i++) {
				list[i] = new ArrayList<>();
			}
			color = new int[v+1];
			for(int i=0; i<e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				list[from].add(to);
				list[to].add(from);
			}
			
			bfs();
			
			System.out.println("===========");
			for(int col : color) {
				System.out.print(col);
			}
			System.out.println();
		}
		System.out.println(sb.toString());
		
	}
	
	static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=1; i<v+1; i++) {
			if(color[i] ==0) {
				q.add(i);
				color[i] =1;
			}
			
			while(!q.isEmpty()) {
				int pos = q.poll();
				
				for(int j=0; j<list[pos].size(); j++) {
					int next = (int)list[pos].get(j);
					
					// 방문하지 않은 노드 Queue 삽입 
					if(color[next] ==0) {
						q.add(next);
					}
					
					// 이분 그레프가 아니면 NO 리턴 
					if(color[next] == color[pos]) {
						sb.append("NO\n");
						return;
					}
					
					if(color[pos] == 1 && color[next] ==0) {
						color[next] =2;
					}else if(color[pos] == 2 && color[next] ==0) {
						color[next] =1;
					}
				}
			}
			
		}
		sb.append("YES\n");		
	}

	
//	static void dfs(int st, boolean flag) {
//		if(flag) {
//			color[st] = 1;
//		}else {
//			color[st] = 2;
//		}		
//		for(int k : color) {
//			System.out.print(k);
//		}
//		System.out.println();
//		for(int i=0; i<list[st].size(); i++) {
//			int dt= (int) list[st].get(i);
////			System.out.println(dt);
//			if(color[dt] ==0) {
//				dfs(dt, !flag);
//			}
//		}
//	}

		
		
	
	
}
