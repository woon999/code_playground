package leetcode;

// #78 Subsets

import java.util.ArrayList;
import java.util.List;

public class Subsets_78 {
	public static void main(String[] args) {
		int[] nums = {1,2,3};

		System.out.println("subsets(nums) = " + subsets(nums));
	}

	public static List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(result, new ArrayList<>(), nums, 0);
		return result;
	}

	static void backtrack(List<List<Integer>> list, List<Integer> box, int[] nums, int idx)  {
		list.add(new ArrayList<>(box));
		for(int i = idx; i < nums.length; i++){
			box.add(nums[i]);
			backtrack(list, box, nums, i + 1);
			box.remove(box.size() - 1);
		}
	}
}
