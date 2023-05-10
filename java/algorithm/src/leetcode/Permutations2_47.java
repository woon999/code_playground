package leetcode;

// #47 - Permutations2
// 중복 제거 - nums 정렬 후 중복은 고려 x
//  Arrays.stream(arr).distinct().toArray();으로 중복 제거하면 중복된 숫자 카운트해줘야 함. 더 번거로움

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations2_47 {
	public static void main(String[] args) {
		int[] nums = {3,3,0,3};

		System.out.println("permuteUnique(nums) = " + permuteUnique(nums));
	}

	public static List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		return permutation(nums, new ArrayList<>(), new ArrayList<>());
	}

	static List<List<Integer>> permutation(int[] nums, List<Integer> list, List<List<Integer>> result) {
		if(list.size() == nums.length){
			result.add(new ArrayList<>(list));
			return result;
		}

		for(int i=0; i<nums.length; i++){
			int v = nums[i];
			if(nums[i] != -100){
				if(i+1 < nums.length && nums[i] == nums[i+1]) continue;
				nums[i] = -100;
				list.add(v);
				permutation(nums, list, result);
				list.remove(list.size()-1);
				nums[i] = v;
			}
		}

		return result;
	}
}
