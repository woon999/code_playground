package leetcode;

// 90 Subsets2

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2_90 {
	public static void main(String[] args) {
		int[] nums = {1,2,2};

		System.out.println("subsetsWithDup(nums) = " + subsetsWithDup(nums));
	}

	public static List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(result, new ArrayList<>(), nums, 0);
		return result;
	}

	static void backtrack(List<List<Integer>> list, List<Integer> box, int[] nums, int idx)  {
		list.add(new ArrayList<>(box));
		for(int i = idx; i < nums.length; i++){
			if(i > idx && nums[i] == nums[i-1]) continue; // skip duplicates

			box.add(nums[i]);
			backtrack(list, box, nums, i + 1);
			box.remove(box.size() - 1);
		}
	}
}
