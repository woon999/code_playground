package leetcode;

// #665 Non-Decreasing Array

public class NonDecreasingArray_665 {

	public static void main(String[] args) {
//		int[] nums = {4, 2, 3};
//		int[] nums = {4, 2, 1};
//		int[] nums = {3, 4, 2, 3};
		int[] nums = {5, 7, 1, 8};
		
		System.out.println(checkPossibility(nums));
	}
	
	public static boolean checkPossibility(int[] nums) {
		int cnt = 0;
		int prev = nums[0];
		
		for(int i=0; i<nums.length-1; i++) {
			int cur = nums[i];
			int nxt = nums[i+1];
			
			if(cur > nxt) {
				cnt++;
				if(i!=0 && prev > nxt) {
					nums[i+1] = nums[i];
				} else {
					nums[i] = nums[i+1];
				}
			}
			
			if(cnt > 1) return false;
			
			prev = nums[i];
		}
		
		return true;
    }
}
