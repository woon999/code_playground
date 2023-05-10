package leetcode;

// #26 - Remove Duplicates from Sorted Array
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicatesFromSortedArray_26 {

	public static void main(String[] args) {
		int[] nums = {0,0,1,1,1,2,2,3,3,4};

		System.out.println("nums = " + removeDuplicates(nums));
	}

	public static int removeDuplicates(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for(int i=0; i<nums.length; i++){
			if(set.add(nums[i])){
				nums[set.size()-1] = nums[i];
			}
		}

		return set.size();
	}
}
