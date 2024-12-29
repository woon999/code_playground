package baekjoon.ttzero.tree;

// #5639
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BinaryTree {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BinaryNode tree =  new BinaryNode(Integer.parseInt(br.readLine()));
		String s = "";
		while(true) {
			
//			if((s = br.readLine()) == null || s.length() == 0) break;
			if((s = br.readLine()) == null || s.isEmpty()) break;
			
			tree = tree.add(tree, Integer.parseInt(s));
			
		}
		
		postorder(tree);
		
		System.out.println(sb.toString());
	}
	
	static void postorder(BinaryNode n) {
		if(n.left != null) postorder(n.left);
		if(n.right != null) postorder(n.right);
		sb.append(n.value + "\n");
	}
}

class BinaryNode{
	int value;
	BinaryNode left;
	BinaryNode right;
	
	public BinaryNode(int value) {
		this.value = value;
	}
	
	public BinaryNode add(BinaryNode n, int v) {
		BinaryNode pos = null;
		if(n == null) return new BinaryNode(v);
		if(n.value > v) {
			pos = add(n.left, v);
			n.left = pos;
		}
		else if(n.value < v) {
			pos = add(n.right, v);
			n.right = pos;
		}
		
		return n;
	}
}