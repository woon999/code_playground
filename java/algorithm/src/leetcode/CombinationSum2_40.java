package leetcode;

// 40 - Combination Sum2

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2_40 {
	public static void main(String[] args) {
		int[] candidates = {10,1,2,7,6,1,5};
		int target = 8;
		System.out.println("combinationSum = " + combinationSum2(candidates, target));
	}

	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		return getCombSum(candidates,0, new ArrayList<>(), 0, target, new ArrayList<>());
	}

	static List<List<Integer>> getCombSum(int[] candidates, int idx, List<Integer> list, int sum, int target, List<List<Integer>> result){
		if (target == sum) {
			result.add(new ArrayList<>(list));
			return result;
		}

		for(int i=idx; i<candidates.length; i++){
			if(i > idx && candidates[i] == candidates[i-1]) continue;

			if(candidates[i] <= target-sum) {
				list.add(candidates[i]);
				getCombSum(candidates, i + 1, list, sum + candidates[i], target, result);
				list.remove(list.size() - 1);
			}
		}
		return result;
	}
}
