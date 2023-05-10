package baekjoon.ttone.tree;

// #2263 tree 트리의 순회  
import java.io.*;
import java.util.StringTokenizer;

public class Traversal2 {

	static int[] inorder, inIdx, postorder;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		
		inorder = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}
		postorder = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}
		
		inIdx = new int[n+1];
		for(int i=1; i<n+1; i++) {
			inIdx[inorder[i]] = i;
		}
	
		traversal(1,n,1,n);
		System.out.println(sb.toString());
	}
	
	static void traversal(int is, int ie, int ps, int pe) {
		if(ie < is || pe < ps) return;
		int root = postorder[pe];
		int rIdx = inIdx[root];
		
//		System.out.println("root :" +root);
//		System.out.println("inorder rootIdx:" +rIdx);
		sb.append(root+" ");
		
		// left 트리 길이
		int len = rIdx - is;  
		// left 트리
		traversal(is, rIdx-1, ps, ps+len-1);
		// right 트리 
		traversal(rIdx+1, ie, ps+len, pe-1);
		
	}

}
//12
//8 4 9 2 5 1 10 6 3 11 7 12
//8 9 4 5 2 10 6 11 12 7 3 1
