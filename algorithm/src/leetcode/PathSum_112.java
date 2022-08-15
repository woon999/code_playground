package leetcode;

// #112 - Path Sum

public class PathSum_112 {
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
		TreeNode node3_1 = new TreeNode(7);
		TreeNode node3_2 = new TreeNode(2);
		TreeNode node3_3 = new TreeNode(1);

		TreeNode node2_1 = new TreeNode(11, node3_1, node3_2);
		TreeNode node2_2 = new TreeNode(13);
		TreeNode node2_3 = new TreeNode(4, null, node3_3);
		TreeNode node1_1 = new TreeNode(4, node2_1, null);
		TreeNode node1_2 = new TreeNode(8, node2_2, node2_3);
		TreeNode root = new TreeNode(5, node1_1, node1_2);

		System.out.println("hasPathSum(root, 3) = " + hasPathSum(root,2));
	}

	public static boolean hasPathSum(TreeNode root, int targetSum) {
		if(root == null) return false;
		return dfs(root, targetSum, root.val);
	}

	public static boolean dfs(TreeNode cur, int targetSum, int sum){
		if(targetSum == sum && cur.left == null && cur.right == null){
			return true;
		}

		boolean f = false;
		if(cur.left != null){
			f |= dfs(cur.left, targetSum, sum+cur.left.val);
		}

		if(cur.right != null){
			f |= dfs(cur.right, targetSum, sum+cur.right.val);
		}

		return f;
	}
}
