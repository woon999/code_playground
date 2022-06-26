package leetcode;

// #1 TwoSum 
import java.util.*;

public class TwoSum_1 {

	public static void main(String[] args) {
//		int[] nums = {2, 7, 11, 15};
//		int target = 9;
		
		int[] nums = {3, 2, 4};
		int target = 6;
		
		System.out.println(Arrays.toString(twoSum(nums, target)));
	}
	
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
}

