package leetcode;

// 39 - Combination Sum

import java.util.ArrayList;
import java.util.List;

public class CombinationSum_39 {
	public static void main(String[] args) {
		int[] candidates = {2,3,6,7};
		int target = 7;
		System.out.println("combinationSum = " + combinationSum(candidates, target));
	}

	public static List<List<Integer>> combinationSum(int[] candidates, int target) {
		return getCombSum(candidates, 0, new ArrayList<>(), 0, target, new ArrayList<>());
	}

	static List<List<Integer>> getCombSum(int[] candidates, int idx, List<Integer> list, int sum, int target, List<List<Integer>> result){
		if(idx == candidates.length){
			if(target == sum){
				result.add(new ArrayList<>(list));
			}
			return result;
		}

		if(candidates[idx] <= target-sum){
			list.add(candidates[idx]);
			getCombSum(candidates, idx, list, sum+candidates[idx], target, result);
			list.remove(list.size()-1);
		}

		getCombSum(candidates, idx+1, list, sum, target, result);
		return result;
	}
}
