package leetcode;

// #16 - 3Sum Closet
import java.util.Arrays;

public class ThreeSumCloset_16 {

	public static void main(String[] args) {
		// int[] nums = {-1, 2, 1, 4};
		// int target = 1;

		int[] nums = {-1, 2, 1, 4};
		int target = 1;

		System.out.println("threeSumClosest = " + threeSumClosest(nums, target));
	}

	public static int threeSumClosest(int[] nums, int target) {
		int min = Integer.MAX_VALUE;
		int answer = 0;
		Arrays.sort(nums);

		// i, l, r == target ?
		for(int i=0; i<nums.length-2; i++){
			if(i>0 && nums[i] == nums[i-1]) continue;

			int l = i+1;
			int r = nums.length -1;

			while(l < r){
				int threeSum = nums[i] + nums[l] + nums[r];
				int diff = Math.abs(threeSum - target);

				if(diff == 0) return threeSum;
				if(diff < min){
					min = diff;
					answer = threeSum;
				}

				if(threeSum > target){
					r--;
				}else{
					l++;
				}

			}
		}
		return answer;
	}

}
