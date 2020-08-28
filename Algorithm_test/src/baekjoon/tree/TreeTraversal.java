package baekjoon.tree;

// #1991
import java.io.*;
import java.util.*;

public class TreeTraversal {

	static List<Node3>[] list;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		list = new ArrayList[n + 1];
		for (int i = 0; i < n + 1; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i < n + 1; i++) {

			String[] s = br.readLine().split(" ");

//			System.out.println(s[0].charAt(0)=='a');

//			System.out.println(s[0].charAt(0) - 'A');
			list[s[0].charAt(0) - 'A'].add(new Node3(s[1].charAt(0), s[2].charAt(0)));
		}

		
		sb = new StringBuilder();
		preorder(0);
		sb.append("\n");
		inorder(0);
		sb.append("\n");
		postorder(0);
		sb.append("\n");
		
		System.out.println(sb.toString());
	}

	static void preorder(int a) {
		for(Node3 node : list[a]) {
			int l = node.left;
			int r = node.right;
			
			sb.append((char)(a+65));
//			System.out.print((char)(a+65));
			if(l != '.') preorder(l - 'A');
			if(r != '.') preorder(r - 'A');
		}
		
	}

	static void inorder(int a) {
		for(Node3 node : list[a]) {
			int l = node.left;
			int r = node.right;
			
			if(l != '.') inorder(l - 'A');
			sb.append((char)(a+65));
			if(r != '.') inorder(r - 'A');
			
		}
	}

	static void postorder(int a) {
		for(Node3 node : list[a]) {
			int l = node.left;
			int r = node.right;
			
			if(l != '.') postorder(l - 'A');
			if(r != '.') postorder(r - 'A');
			sb.append((char)(a+65));
		}
	}
}

class Node3 {
	char left;
	char right;

	public Node3(char left, char right) {
		this.left = left;
		this.right = right;

	}
}
//7
//A B C
//B D .
//C E F
//E . .
//F . G
//D . .
//G . .