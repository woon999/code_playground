package leetcode;

// 136 - Single Number

// wow wow!!
// ref: https://leetcode.com/problems/single-number/discuss/1771771/Think-it-through-oror-Time%3A-O(n)-Space%3A-O(1)-oror-Python-Explained

import java.util.Arrays;

public class SingleNumber_136 {
	public static void main(String[] args) {
		int[] nums = {4, 4, 1, 3, 2, 1, 2};
		System.out.println("singleNumber(nums) = " + singleNumber(nums));
	}

	public static int singleNumber_wow(int[] nums) {
		int xor = 0;
		for (int num : nums) {
			xor ^= num;
		}
		return xor;
	}

	public static int singleNumber(int[] nums) {
		Arrays.sort(nums);
		int i = 0;
		for (i = 0; i < nums.length - 1; i += 2) {
			if (nums[i] != nums[i + 1]) {
				break;
			}
		}

		if (i == nums.length - 1) {
			return nums[nums.length - 1];
		}
		return nums[i];
	}
}
