package baekjoon.ttone.tree;

// #2233 tree 사과나무 - lca
import java.io.*;
import java.util.*;

public class AppleTree {

	static int[][] parent;
	static int[] depth, nodeIdx;
	static int n, size, s, e, i, j;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		char[] data = br.readLine().toCharArray();
		size = (int)Math.ceil(Math.log(n)/Math.log(2))+1;
		parent = new int[n+1][size];
		depth = new int[n+1];
		nodeIdx = new int[2*n+1];
		int idx = 1;
		int cur = 0;
		for(int i=1; i<=data.length; i++) {
			if(data[i-1]-'0' == 0) {
				parent[idx][0] = cur;
				depth[idx] = depth[cur] +1;
				nodeIdx[i] = idx; 
				cur = idx++;
			}else {
				nodeIdx[i] = cur;
				cur = parent[cur][0];
			}
			
		}
		fillParents();
		print();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		int lcaIdx = lca(nodeIdx[s], nodeIdx[e]);
		
		for(int i=1; i<n*2+1; i++) {
			if(nodeIdx[i]==lcaIdx) {
				System.out.print(i+" ");
			}
		}
		
	}
	
	static void fillParents() {
		for(int i=1; i<size; i++) {
			for(int j=1; j<n+1; j++) {
				parent[j][i] = parent[parent[j][i-1]][i - 1];
			}
			
		}
	}
	
	static int lca(int x, int y) {
		if(depth[x] > depth[y]) {
			int tmp = x;
			x = y;
			y = tmp;
		}
//		System.out.println(x+","+ y);
		for(int i= size-1; i>=0; i--) {
			if(depth[x] <= depth[parent[y][i]]) {
				y = parent[y][i];
			}
		}
		
//		System.out.println(x+", " + y);
		if(x == y) return x;
		
		for(int i= size-1; i>=0; i--) {
			if(parent[x][i]!=parent[y][i]) {
				x = parent[x][i];
				y = parent[y][i];
			}
		}
//		System.out.println(parent[x][0]);
		return parent[x][0];
	}
	
	static void print() {
		System.out.println("depth----");
		for(int num : depth) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		System.out.println("nodeIdx----");
		for(int num : nodeIdx) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		System.out.println("parent----");
		for(int i=0; i<size; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(parent[j][i]+" ");
			}
			System.out.println();	
		}
		
	}
}

