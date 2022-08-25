package leetcode;

// #144 - Binary Tree Postorder Traversal
import java.util.ArrayList;
import java.util.List;

public class BinaryTreePostorderTraversal_145 {
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

		System.out.println("postorderTraversal(root) = " + postorderTraversal(root));
	}

	public static List<Integer> postorderTraversal(TreeNode root) {
		return postorder(root, new ArrayList<>());
	}

	private static List<Integer> postorder(TreeNode root, List<Integer> list){
		if(root == null) return list;

		postorder(root.left, list);
		postorder(root.right, list);
		list.add(root.val);

		return list;
	}
}
