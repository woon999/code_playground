package leetcode;

// #94 - BinaryTree Inorder Traversal
import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal_94 {

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode() {}
		TreeNode(int val) { this.val = val; }
		TreeNode(int val, TreeNode left, TreeNode right) {
		  this.val = val;this.left = left;
		  this.right = right;
		}
	}
	public static void main(String[] args) {
		TreeNode leaf = new TreeNode(3);
		TreeNode two = new TreeNode(2, leaf, null);
		TreeNode root = new TreeNode(1, null, two);

		System.out.println("inorderTraversal() = " + inorderTraversal(root));
	}

	public static List<Integer> inorderTraversal(TreeNode root) {
		if(root == null){
			return List.of();
		}

		List<Integer> result = new ArrayList<>();
		traversal(root, result);
		return result;
	}

	public static void traversal(TreeNode root, List<Integer> result){
		if(root.left != null){
			traversal(root.left, result);
		}
		result.add(root.val);
		if(root.right != null){
			traversal(root.right, result);
		}
	}

}
