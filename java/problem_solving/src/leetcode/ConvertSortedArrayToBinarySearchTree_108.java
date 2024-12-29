package leetcode;

// #108 - Convert Sorted Array To Binary Search Tree

public class ConvertSortedArrayToBinarySearchTree_108 {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() { }
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
		int[] nums = {-10, -3, 0, 5, 9};

		System.out.println("sortedArrayToBST(nums) = " + sortedArrayToBST(nums));

		TreeNode node = sortedArrayToBST(nums);
		print(node);
	}

	static void print(TreeNode node){
		if(node == null) return;

		print(node.left);
		System.out.println(node.val);
		print(node.right);
	}

	public static TreeNode sortedArrayToBST(int[] nums) {
		return divide(nums, 0, nums.length);
	}

	static TreeNode divide(int[] nums, int s, int e) {
		int mid = (s + e) / 2;

		if (mid < 0 || mid > nums.length - 1)return null;
		if(s > e) return null;

		TreeNode node = new TreeNode(nums[mid]);

		node.left = divide(nums, s, mid - 1);
		node.right = divide(nums, mid + 1, e);

		return node;
	}
}
