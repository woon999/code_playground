package leetcode;

// #113 - Path Sum2

import java.util.*;

public class PathSum2_113 {
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
		TreeNode node3_3 = new TreeNode(5);
		TreeNode node3_4 = new TreeNode(1);

		TreeNode node2_1 = new TreeNode(11, node3_1, node3_2);
		TreeNode node2_2 = new TreeNode(13);
		TreeNode node2_3 = new TreeNode(4, node3_3, node3_4);
		TreeNode node1_1 = new TreeNode(4, node2_1, null);
		TreeNode node1_2 = new TreeNode(8, node2_2, node2_3);
		TreeNode root = new TreeNode(5, node1_1, node1_2);

		// TreeNode root = new TreeNode();
		System.out.println("pathSum(root) = " + pathSum(root, 22));
	}


	public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
		if(root == null) return List.of(List.of());

		List<List<Integer>> results = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();
		stack.add(root.val);

		dfs(root, targetSum, root.val, stack, results);
		return results;
	}

	public static void dfs(TreeNode cur, int targetSum, int sum, Stack<Integer> result, List<List<Integer>> results){
		if(targetSum == sum && cur.left == null && cur.right == null){
			results.add(new ArrayList<>(result));
			System.out.println("results = " + results);
			return;
		}

		if(cur.left != null){
			result.push(cur.left.val);
			dfs(cur.left, targetSum, sum+cur.left.val, result, results);
			result.pop();
		}

		if(cur.right != null){
			result.push(cur.right.val);
			dfs(cur.right, targetSum, sum+cur.right.val, result, results);
			result.pop();
		}

		return;
	}
}
