package baekjoon.ttone.tree;

// #9934 트리 완전 이진 트리 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class FullBinaryTree {

	static int[] node;
	static List<Integer>[] nodeList;
	static int size, h;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		size = (int)Math.pow(2, n)-1;
		node = new int[size];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<size; i++) {
			node[i] = Integer.parseInt(st.nextToken());
		}
		
		h = (int)Math.ceil(Math.log(size)/Math.log(2))+1;
		nodeList = new ArrayList[h];
		for(int i=0; i<h; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		solve(0,size-1,0);
		
		for(int i=0; i<h; i++) {
			for(int nd : nodeList[i]) {
				System.out.print(nd+" ");
			}
			System.out.println();
		}
	}
	
	static void solve(int s, int e, int depth) {
		if(s>e) return;
		int mid = (s+e)/2;
		
		nodeList[depth].add(node[mid]);
		
		// left :  s ~ mid-1  
		solve(s,mid-1,depth+1);
		// right : mid+1 ~ e
		solve(mid+1,e,depth+1);
	}
}

