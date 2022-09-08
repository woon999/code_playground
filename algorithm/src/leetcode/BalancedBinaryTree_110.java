package leetcode;

// #110 - Balanced Binary Tree

public class BalancedBinaryTree_110 {
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
		// TreeNode n2_2 = new TreeNode(7);
		// TreeNode n2_1 = new TreeNode(15);
		//
		// TreeNode n1_2 = new TreeNode(20, n2_1, n2_2);
		// TreeNode n1_1 = new TreeNode(9);
		//
		// TreeNode root = new TreeNode(3, n1_1, n1_2);

		TreeNode n3_2 = new TreeNode(4);
		TreeNode n3_1 = new TreeNode(4);

		TreeNode n2_2 = new TreeNode(3);
		TreeNode n2_1 = new TreeNode(3, n3_1, n3_2);

		TreeNode n1_2 = new TreeNode(2);
		TreeNode n1_1 = new TreeNode(2, n2_1, n2_2);

		TreeNode root = new TreeNode(1, n1_1, n1_2);
		System.out.println("isBalanced(root) = " + isBalanced(root));
	}

	public static boolean isBalanced(TreeNode root) {
		return calIsBalanced(root) != -1;
	}

	public static int calIsBalanced(TreeNode root){
		if(root == null) return 0;

		int l = calIsBalanced(root.left);
		if(l == -1) return -1;

		int r = calIsBalanced(root.right);
		if(r == -1) return -1;

		if(Math.abs(l - r) > 1) return -1;

		return Math.max(l, r) + 1;
	}
}
