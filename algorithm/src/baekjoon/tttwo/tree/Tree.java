package baekjoon.tttwo.tree;

//  #10838 tree 트리 - LCA 
import java.io.*;
import java.util.*;

public class Tree {

	static class Node{
		int pa;
		int color;
		public Node(int pa, int color) {
			this.pa = pa;
			this.color = color;
		}
	}
	static final int MAX_LEN = 1000;
	static int n;
	static Node[] nodes;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		nodes = new Node[n];
		for(int i=0; i<n; i++) {
			nodes[i] = new Node(0,0);
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(r==1) {
				int c = Integer.parseInt(st.nextToken());
				paint(a,b,c);
			}else if(r==2) {
				move(a,b);
			}else {
				sb.append(count(a,b)+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	static void move(int a, int b) {
		nodes[a].pa = b;
	}
	
	static int findLCA(int a, int b) {
		if(a==b) return a;
		
		boolean[] check = new boolean[n];
		for(int i=0; i<MAX_LEN; i++) { // a 노드 부모 탐색 
			check[a] = true;
			a = nodes[a].pa;
		}
		
		for(int i=0; i<MAX_LEN; i++) { // b 노드 부모 탐색
			if(check[b]) return b;
			b = nodes[b].pa;
		}
		return 0;
	}
	
	static void paint(int a, int b, int c) {
		int lca = findLCA(a,b);
		while(a != lca) {
			nodes[a].color = c;
			a = nodes[a].pa;
		}
		
		while(b != lca) {
			nodes[b].color = c;
			b = nodes[b].pa;
		}
	}
	
	static int count(int a, int b) {
		int lca = findLCA(a,b);
		Set<Integer> set = new HashSet<>();
		while(a != lca) {
			set.add(nodes[a].color);
			a = nodes[a].pa;
		}
		
		while(b != lca) {
			set.add(nodes[b].color);
			b = nodes[b].pa;
		}
		return set.size();
	}
}
