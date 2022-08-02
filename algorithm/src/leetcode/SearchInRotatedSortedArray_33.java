package leetcode;

// 33 - Search In Rotated Sorted Array

public class SearchInRotatedSortedArray_33 {
	public static void main(String[] args) {
		int[] nums = {4,5,6,7,0,1,2};
		int target = 0;

		System.out.println("search(nums, target) = " + search(nums, target));
	}

	public static int search(int[] nums, int target) {
		int idx = -1;
		int s = 0;
		int e = nums.length-1;
		while(s <= e){
			int mid = (s+e)/2;

			if(nums[mid] == target){
				idx = mid;
				break;
			}

			if(nums[mid] >= nums[s]){
				if(target >= nums[s] && target <= nums[mid]) { // target in [s, mid]
					e = mid - 1;
				}else { // target in [mid, e]
					s = mid + 1;
				}
			} else {
				if(target >= nums[mid] && target <= nums[e]){ // target in [mid ,e]
					s = mid + 1;
				} else { // target in [s, mid]
					e = mid - 1;
				}
			}
		}

		return idx;
	}
}
