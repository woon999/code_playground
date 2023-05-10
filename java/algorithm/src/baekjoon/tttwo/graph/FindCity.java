package baekjoon.tttwo.graph;

// #18352 graph 특정 거리의 도시 찾기 - bfs
import java.io.*;
import java.util.*;

public class FindCity {

	static int n;
	static List<Integer> result;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		result = new ArrayList<>();
		bfs(x, k);
		
		if(result.size() == 0) {
			System.out.println(-1);
			return;
		}
		
		Collections.sort(result);
		for(int num : result) {
			System.out.println(num);
		}
				
	}
	
	static void bfs(int start, int k) {
		Queue<int[]> q = new LinkedList<>();
		boolean[] check = new boolean[n+1];
		check[start] = true;
		q.add(new int[] {start, 0});
		
		while(!q.isEmpty()) {
			int[] p = q.poll();
			int pNode = p[0];
			int pDis = p[1];
			
			if(pDis == k) {
				result.add(pNode);
				continue;
			}
		
			
			for(int nxt : list[pNode]) {
				if(!check[nxt]) {
					check[nxt] = true;
					q.add(new int[] {nxt, pDis+1});
				}
			}
		}
		
	}
}
