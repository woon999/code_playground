package baekjoon.ttzero.tree;

// #2263
import java.io.*;
import java.util.*;

public class TreeTraversal2 {

	static int n;
	static int[] inOrder;
	static int[] inIdx;
	static int[] postOrder;
	
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 n = Integer.parseInt(br.readLine());
		 
		 inOrder = new int[n+1];
		 inIdx = new int[n+1];
		 postOrder = new int[n+1];
		 
		 sb = new StringBuilder();
		 
		 StringTokenizer st = new StringTokenizer(br.readLine());
		 for(int i=1; i<n+1; i++) {
			 inOrder[i] = Integer.parseInt(st.nextToken());
		 }
		 
		 st = new StringTokenizer(br.readLine());
		 for(int i=1; i<n+1; i++) {
			 postOrder[i] = Integer.parseInt(st.nextToken());
		 }
		 for(int i=1; i<n+1; i++) {
			 inIdx[inOrder[i]] = i;
		 }
		 
		 
		 getPreorder(1, n, 1, n);
		 System.out.println(sb.toString());
		
	}
	
	static void getPreorder(int is, int ie, int ps, int pe) {
		if(is > ie || ps > pe) return;
		
		int root = postOrder[pe];
		sb.append(root+" ");
		
//		inorder의 루트 인덱스 찾기 
		int nir = inIdx[root];
		
//		inorder 루트 기준 왼쪽에 몇개 있는지 계산 
		int l = nir - is;
		
//		좌측 자식 노드 
		getPreorder(is, nir-1, ps, ps+l-1);
//		우측 자식 노드 
		getPreorder(nir+1, ie, ps+l, pe-1);
	}

}
