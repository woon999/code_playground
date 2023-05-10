package leetcode;

// 169 - Majority Element

public class MajorityElement_169 {
	public static void main(String[] args) {
		int[] nums = {2,2,1,1,1,2,2};
		System.out.println("majorityElement(nums) = " + majorityElement(nums));
	}

	public static int majorityElement(int[] nums) {
		int res = nums[0];
		int count = 1;
		for (int i = 0; i < nums.length; i++) {
			if(res == nums[i]) count++;
			else count--;

			if(count == 0){
				res = nums[i];
				count = 1;
			}
		}

		return res;
	}
}
