package leetcode;

// #15 - ThreeSum
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum_15 {
	public static void main(String[] args) {
		int[] nums = {-1,0,1,2,-1,-4};
		System.out.println("nums = " + threeSum(nums));
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> answer = new ArrayList<>();
		Arrays.sort(nums);

		// i, l, r == 0 ?
		for(int i=0; i<nums.length-2; i++){
			if(i>0 && nums[i] == nums[i-1]) continue;

			int l = i+1;
			int r = nums.length -1;

			while(l < r){
				int threeSum = nums[i] + nums[l] + nums[r];

				if(threeSum == 0){
					answer.add(List.of(nums[i], nums[l], nums[r]));
					l++;
					r--;
					// 중복 pass
					while(nums[l] == nums[l-1] && l < r) l++;
					while(nums[r] == nums[r+1] && l < r) r--;
				}else if(threeSum > 0){
					r--;
				}else{
					l++;
				}

			}
		}

		return answer;
	}
}
