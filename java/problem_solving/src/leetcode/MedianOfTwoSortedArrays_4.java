package leetcode;

// #4 - Median Of Two Sorted Arrays
import java.util.Arrays;

public class MedianOfTwoSortedArrays_4 {
	public static void main(String[] args) {
		int[] nums1 = {1, 2};
		int[] nums2 = {3, 4};
		System.out.println("findMedianSortedArrays(nums1, nums2) = " + findMedianSortedArrays(nums1, nums2));
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length;
		int m = nums2.length;
		int[] nums = new int[n + m];

		for (int i = 0; i < n; i++) {
			nums[i] = nums1[i];
		}

		for (int i = n; i < n + m; i++) {
			nums[i] = nums2[i - n];
		}
		Arrays.sort(nums);

		int nl = nums.length;
		if ((nl & 1) == 1) {
			return nums[nl / 2];
		} else {
			return ((double)nums[(nl-1) / 2] + nums[nl / 2]) / 2;
		}
	}
}
