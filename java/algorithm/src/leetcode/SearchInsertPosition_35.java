package leetcode;

// #35 - Search Insert Position
public class SearchInsertPosition_35 {
	public static void main(String[] args) {
		int[] nums = {1,3,5,6};
		int target =0;

		System.out.println(searchInsert(nums, target));
	}


	public static int searchInsert(int[] nums, int target) {
		int l =0, r = nums.length-1;

		while(l <= r){
			int mid = (l+r)/2;
			if(target == nums[mid]){
				return mid;
			} else if(target < nums[mid]){
				r = mid-1;
			}else{
				l = mid+1;
			}
		}
		if(l > r) return r+1;
		return 1;
	}
}
