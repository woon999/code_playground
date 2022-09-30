package leetcode;

// #53 - Maximum Subarray

public class MaximumSubarray_53 {
	public static void main(String[] args) {
		int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
		System.out.println("maxSubArray(nums) = " + maxSubArray(nums));
	}

	public static int maxSubArray(int[] nums) {
		if(nums.length == 1) return nums[0];

		int max = nums[0], sum = 0;
		for (int num : nums) {
			sum += num;
			if(sum > max) {
				max = sum;
			}
			if(sum < 0) sum = 0;
		}
		return max;
	}
}
