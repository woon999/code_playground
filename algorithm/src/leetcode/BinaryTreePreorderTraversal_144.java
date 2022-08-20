package leetcode;

// #144 - Binary Tree Preorder Traversal
import java.util.ArrayList;
import java.util.List;

public class BinaryTreePreorderTraversal_144 {
	static class TreeNode {
	  int val;
	  TreeNode left;
	  TreeNode right;
	  TreeNode() {}
	  TreeNode(int val) { this.val = val; }
	  TreeNode(int val, TreeNode left, TreeNode right) {
		  this.val = val;
		  this.left = left;
		  this.right = right;
	  }
	}

	public static void main(String[] args) {
		TreeNode n2 = new TreeNode(3);
		TreeNode n1 = new TreeNode(2, n2, null);
		TreeNode root = new TreeNode(1, null, n1);

		System.out.println("preorderTraversal(root) = " + preorderTraversal(root));
	}

	public static List<Integer> preorderTraversal(TreeNode root) {
		return preorder(root, new ArrayList<>());
	}

	private static List<Integer> preorder(TreeNode root, List<Integer> list){
		if(root == null) return list;

		list.add(root.val);
		preorder(root.left, list);
		preorder(root.right, list);

		return list;
	}
}
