package baekjoon.ttone.graph;

// #1325 graph 효율적인 해킹 
import java.io.*;
import java.util.*;

public class EffectiveHacking {

	static int n;
	static int[] rank;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		rank = new int[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
		}
		
		int max = Integer.MIN_VALUE;
		for(int i=1; i<n+1; i++) {
			bfs(i);
		}
		
		for(int i=1; i<n+1; i++) {
			max = Math.max(rank[i], max);
		}
		
		for(int i=1; i<n+1; i++) {
			if(max == rank[i]) {
				bw.write(i+" ");
			}
		}
		bw.flush();
		bw.close();
	}
	
	
	static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] check = new boolean[n+1];
		q.add(start);
		check[start] = true;
		while(!q.isEmpty()) {
			int pos = q.poll();
			
			for(int next : list[pos]) {
				if(!check[next]) {
					check[next] = true;
					rank[next]++;
					q.add(next);
				}
			}
		}
	}
}
