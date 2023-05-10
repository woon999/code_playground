package leetcode;

// #219 - Contains Duplicate2

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate2_219 {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 1};
		int k = 3;
		System.out.println("containsNearbyDuplicate(nums) = " + containsNearbyDuplicate(nums,k));
	}

	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		int i = 0;
		Set<Integer> set = new HashSet<>();
		for(int j = 0; j < nums.length; j++) {
			if(!set.add(nums[j])){
				return true;
			}
			if(set.size() >= k+1){ // refresh
				set.remove(nums[i++]);
			}
		}
		return false;
	}
}
