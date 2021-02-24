package baekjoon.unionfind;


// Union find #1 집합의 합 

import java.io.*;
import java.util.*;

public class SumOfSet {
	static int[] parent; 
	
	static class SumNode {
		int type;
		int from;
		int to;
		
		SumNode(int type, int from, int to){
			this.type = type;
			this.from = from;
			this.to = to;
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Queue<SumNode> q = new LinkedList<>();
		parent = new int[n+2];
		StringBuilder sb = new StringBuilder(); 
		
		for(int i =0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			q.add(new SumNode(a, b, c));
		}
		
		for(int i=0; i<n+2; i++) {
			parent[i] = i;
		}
		
		while(!q.isEmpty()) {
			SumNode pos = q.poll();
			
			int t = pos.type;
			int x = pos.from;
			int y = pos.to;
			
			if(t == 0) {
				if(find(x) == find(y)) continue;
				else {
					union(y,x);
				}
			}else {
				if(find(x) == find(y)) {
					sb.append("YES");
					sb.append("\n");
				}else {
					sb.append("NO");
					sb.append("\n");
				}
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static int find(int n) {
		if(parent[n] == n ) return n;
		
		return parent[n] = find(parent[n]);
	}
	
	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		
		if(ra != rb) {
			parent[rb] = ra;
		}
	}
}


