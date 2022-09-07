package leetcode;


// #101 - Symmetric Tree

public class SymmetricTree_101 {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) {
		// TreeNode n4_2 = new TreeNode(3);
		// TreeNode n4_1 = new TreeNode(4);
		//
		// TreeNode n3_2 = new TreeNode(4);
		// TreeNode n3_1 = new TreeNode(3);
		//
		// TreeNode n1_2 = new TreeNode(2, n4_1, n4_2);
		// TreeNode n1_1 = new TreeNode(2, n3_1, n3_2);
		//
		// TreeNode root = new TreeNode(1, n1_1, n1_2);

		// TreeNode n4_2 = new TreeNode(3);
		// TreeNode n4_1 = new TreeNode(3);

		// TreeNode n3_2 = new TreeNode(4);
		// TreeNode n3_1 = new TreeNode(3);
		//
		// TreeNode n1_2 = new TreeNode(2, n4_1, null);
		// TreeNode n1_1 = new TreeNode(2, n3_1, null);
		//
		// TreeNode root = new TreeNode(1, n1_1, n1_2);

		TreeNode n1_2 = new TreeNode(3);
		TreeNode n1_1 = new TreeNode(2);

		TreeNode root = new TreeNode(1, n1_1, n1_2);

		System.out.println("isSymmetric(root) = " + isSymmetric(root));

	}

	public static boolean isSymmetric(TreeNode root) {
		if(root == null) return true;
		return isSymmetric(root.left, root.right);
	}

	static boolean isSymmetric(TreeNode left, TreeNode right) {
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;

		if(left.val != right.val) return false;

		return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
	}
}
