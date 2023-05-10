package leetcode;


// #226 - Invert Binary Tree
public class InvertBinaryTree_226 {

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
		TreeNode n3_2 =  new TreeNode(9);
		TreeNode n3_1 = new TreeNode(6);
		TreeNode n2_2 = new TreeNode(3);
		TreeNode n2_1 = new TreeNode(1);
		TreeNode n1_2 = new TreeNode(7, n3_1, n3_2);
		TreeNode n1_1 = new TreeNode(2, n2_1, n2_2);
		TreeNode root = new TreeNode(4, n1_1, n1_2);
		System.out.println("invertTree(root) = " + invertTree(root));
	}

	public static TreeNode invertTree(TreeNode root) {
		invert(root);
		return root;
	}

	public static void invert(TreeNode root){
		if (root == null) return;
		invert(root.left);
		invert(root.right);

		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
	}
}
