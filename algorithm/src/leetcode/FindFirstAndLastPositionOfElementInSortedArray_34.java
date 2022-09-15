package leetcode;

// #34 - Find First And Last Position Of Element In Sorted Array
import java.util.Arrays;

public class FindFirstAndLastPositionOfElementInSortedArray_34 {
	public static void main(String[] args) {
		int[] nums = {5, 7, 7, 8, 8, 10};
		int target = 6;

		// int[] nums = {1};
		// int target = 1;

		System.out.println("searchRange(nums,targer) = " + Arrays.toString(searchRange(nums, target)));
	}

	public static int[] searchRange(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		int[] result = {-1, -1};
		while (l <= r) {
			int mid = (l + r) / 2;
			int t = nums[mid];
			if (t < target) {
				l = mid + 1;
			} else {
				if (t == target) result[0] = mid;
				r = mid - 1;
			}
		}

		l = 0;
		r = nums.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			int t = nums[mid];
			if (t > target) {
				r = mid - 1;
			} else {
				if (t == target) result[1] = mid;
				l = mid + 1;
			}
		}
		return result;
	}
}
