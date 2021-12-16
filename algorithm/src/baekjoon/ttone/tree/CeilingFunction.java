package baekjoon.ttone.tree;

// #12767 tree Ceiling Function - bst
import java.io.*;
import java.util.*;

public class CeilingFunction {

	static class Node{
		int data;
		Node left;
		Node right;
		
		Node(int data){
			this.data = data;
			this.left = null;
			this.right = null;
		}
	}
		
	static class BinarySearchTree{
		Node root;
		List<Integer> shape = new ArrayList<>();
		int idx;
		
		void insert(int num) {
			Node insert = new Node(num);
			idx = 1;
			if(root == null) {
				root = insert;
				shape.add(idx);
				return;
			}
			
			Node cur = root, pa = null;
			while(true) {
				pa = cur;
				if(cur.data > num) {
					cur = cur.left;
					idx = idx*2;
					if(cur == null) {
						pa.left = insert;
						shape.add(idx);
						return;
					}
				}else {
					cur = cur.right;
					idx = idx*2+1;
					if(cur == null) {
						pa.right = insert;
						shape.add(idx);
						return;
					}
				}
				
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		for(int i=0; i<n; i++) {
			BinarySearchTree tree = new BinarySearchTree();
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<k; j++) {
				int num = Integer.parseInt(st.nextToken());
				tree.insert(num);
			}
			Collections.sort(tree.shape);
			StringBuilder sb = new StringBuilder();
			for(int num : tree.shape) {
//				System.out.print(num+" ");
				sb.append(num);
			}
			set.add(sb.toString());
//			System.out.println();
		}
		
		System.out.println(set.size());
		
	}
}
