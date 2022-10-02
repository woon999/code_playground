package leetcode;

// #1 TwoSum 
import java.util.*;

public class TwoSum_1 {

	public static void main(String[] args) {
//		int[] nums = {2, 7, 11, 15};
//		int target = 9;
		
		int[] nums = {3, 2, 4};
		int target = 6;
		
		System.out.println(Arrays.toString(twoSum_improvement(nums, target)));
	}

	// O(n^2)
	public static int[] twoSum(int[] nums, int target) {
		int n = nums.length;
		for(int i=0; i<n; i++) {
			for(int j=0; j<i; j++) {
				int sum = nums[i] + nums[j];
				if(sum == target) {
					return new int[] {j, i};
				}
			}
		}
		
		return new int[] {0,1};
    }

	// O(n)
	public static int[] twoSum_improvement(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0; i<nums.length; i++){
			int num = nums[i];
			if(map.containsKey(target - num)){
				return new int[]{map.get(target - num), i};
			}
			map.put(num, i);
		}

		return new int[] {0,1};
	}
}

