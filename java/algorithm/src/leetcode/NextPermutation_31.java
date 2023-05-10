package leetcode;

// #31 - Next Permutation

public class NextPermutation_31 {
	public static void main(String[] args) {
		int[] nums = {1, 3, 2};
		nextPermutation(nums);
	}

	public static void nextPermutation(int[] nums) {
		int i, j;
		for (i = nums.length - 2; i >= 0; i--) { // find increase number
			if (nums[i] < nums[i + 1]) {
				break;
			}
		}

		if (i == -1) { // last
			reverse(nums, 0, nums.length-1);
		} else {
			for (j = nums.length - 1; j > i; j--) { // find one more increase number
				if (nums[j] > nums[i]) {
					break;
				}
			}

			swap(nums, j, i);
			reverse(nums, i + 1, nums.length-1);
		}
	}

	static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	static void reverse(int[] nums, int s, int e) {
		while (s < e) {
			swap(nums, s, e);
			s++;
			e--;
		}
	}
}
