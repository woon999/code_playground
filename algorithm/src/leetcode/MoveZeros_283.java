package leetcode;

// #283 - Move Zeros

import java.util.Arrays;

public class MoveZeros_283 {
	public static void main(String[] args) {
		int[] nums = {0,1,0,3,12};
		moveZeroes(nums);
	}

	public static void moveZeroes(int[] nums) {
		int here = 0;
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] != 0) {
				nums[here++] = nums[i];
			}
		}
		Arrays.fill(nums, here, n, 0);
	}
}
