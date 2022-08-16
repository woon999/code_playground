package leetcode;

// #104 - Maximum Depth Of Binary Tree

public class MaximumDepthOfBinaryTree_104 {
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
		TreeNode n4 =  new TreeNode(15);
		TreeNode n3 = new TreeNode(7);
		TreeNode n2 = new TreeNode(20);
		TreeNode n1 = new TreeNode(9, n3, n4);
		TreeNode root = new TreeNode(3, n1, n2);

		System.out.println("maxDepth() = " + maxDepth(root));
	}

	public static int maxDepth(TreeNode root) {
		if(root == null) return 0;

		int left = maxDepth(root.left);
		int right = maxDepth(root.right);

		return Math.max(left, right) + 1;
	}
}
