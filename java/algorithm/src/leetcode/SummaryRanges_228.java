package leetcode;

// #28 - Summary Ranges

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges_228 {

	public static void main(String[] args) {
		int[] nums = {0, 2, 4, 5, 7, 8};

		System.out.println("summaryRanges(nums) = " + summaryRanges(nums));
	}

	public static List<String> summaryRanges(int[] nums) {
		if (nums.length == 0) return List.of();

		List<String> result = new ArrayList<>();
		int s = 0;
		int prev = nums[0];

		s = prev;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] - 1 != prev) {
				result.add(summaryRange(nums, s, i));
				s = nums[i];
			}

			prev = nums[i];
		}

		result.add(summaryRange(nums, s, nums.length));
		return result;
	}

	private static String summaryRange(int[] nums, int s, int i) {
		if (s == nums[i - 1]) {
			return s+"";
		} else {
			return s +"->"+nums[i - 1];
		}
	}
}
