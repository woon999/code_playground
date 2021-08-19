package baekjoon.ttone.tree;

// #2250 tree 트리의 높이와 너비 - dfs, 중위순회 
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TreeHAndW {

	static class Node{
		int left;
		int right;
		
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	static List<Node>[] list;
	static List<Integer>[] nodeChart;
	static int height=1, row=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		nodeChart = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
			nodeChart[i] = new ArrayList<>();
		}
		
		int[] ranks = new int[n+1];
		StringTokenizer st = null;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int mid = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			if(left!=-1) ranks[left]++;
			if(right!=-1) ranks[right]++;
			list[mid].add(new Node(left, right));
		}
		
		int root = -1;
		for(int i=1; i<n+1; i++) {
			if(ranks[i]==0) {
				root=i;
				break;
			}
		}
		
		traversal(root);
		int max = -1, level=0;
		for(int i=1; i<n+1; i++) {
			int len = nodeChart[i].size();
			if(len>0) {
//				System.out.print(i+" : (");
				int s = nodeChart[i].get(0);
				int e = nodeChart[i].get(len-1);
				int width = e-s+1;
//				System.out.print(width+" ");
					
				if(max < width) {
					max = width;
					level=i;
				}
//				System.out.println(")");
			}
		}
		
		System.out.println(level+" "+max);
	}
	
	// 중위 순회 
	static void traversal(int node) {
		for(Node nxt : list[node]) {
//			System.out.println("#");
			if(nxt.left != -1) {
				height++;
				traversal(nxt.left);
			}
			row++;
//			System.out.println(height + " : " +node+"," + row);
			nodeChart[height].add(row);
			if(nxt.right!= -1) {
				height++;
				traversal(nxt.right);
			}
			height--;
		}
	}
}
