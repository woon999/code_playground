package leetcode;

// #27 - Remove Element

public class RemoveElement_27 {
	public static void main(String[] args) {
		int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
		int val = 2;

		System.out.println("val = " + removeElement(nums, val));
	}

	public static int removeElement(int[] nums, int val) {
		int idx = 0;
		for(int i=0; i<nums.length; i++){
			if(nums[i] != val){
				nums[idx++] = nums[i];
			}
		}
		return idx;

	}
}
