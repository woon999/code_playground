package leetcode;

// #111 - Minimum Depth of BinaryTree
public class MinimumDepthofBinaryTree_111 {
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
		TreeNode n4_2 = new TreeNode(7);
		TreeNode n4_1 = new TreeNode(15);
		TreeNode n1_2 = new TreeNode(20, n4_1, n4_2);
		TreeNode n1_1 = new TreeNode(9);
		TreeNode root = new TreeNode(3, n1_1, n1_2);
		System.out.println("minDepth(root) = " + minDepth(root));
	}

	public static int minDepth(TreeNode root) {
		if(root == null) return 0;

		int left = minDepth(root.left);
		int right = minDepth(root.right);

		return left == 0 | right == 0 ? Math.max(left, right) + 1 : Math.min(left, right) + 1;
	}
}