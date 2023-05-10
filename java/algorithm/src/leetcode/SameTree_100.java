package leetcode;

// #100 - SameTree

public class SameTree_100 {

	public static class TreeNode {
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
		TreeNode leaf1 = new TreeNode(2);
		TreeNode root1 = new TreeNode(1, null, leaf1);

		TreeNode leaf2 = new TreeNode(2);
		TreeNode root2 = new TreeNode(1, leaf2, null);

		System.out.println("isSameTree(root1, root2) = " + isSameTree(root1, root2));
	}

	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if(p == null && q == null){
			return true;
		}else if(p == null || q == null){
			return false;
		}

		if(p.val == q.val){
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}

		return false;
	}
}
