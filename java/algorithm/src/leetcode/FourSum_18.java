package leetcode;

// #18 - FourSum

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum_18 {
	public static void main(String[] args) {
		// int[] nums = {1, 0, -1, 0, -2, 2};
		// int target = 0;

		int[] nums = {1000000000,1000000000,1000000000,1000000000};
		int target = -294967296;
		System.out.println("fourSum(nums, target) = " + fourSum(nums, target));
	}

	// n = 200
	// nums[i] 10^9
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums); // O(nlogn)

		int n = nums.length;

		List<List<Integer>> answer = new ArrayList<>();

		System.out.println(Integer.MIN_VALUE);
		long fSum = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {

				fSum = (long)target - (nums[i] + nums[j]);

				int s = j + 1;
				int e = n - 1;

				while (s < e) {
					int tSum = nums[s] + nums[e];

					if(fSum > tSum){
						s++;
					}else if(fSum < tSum){
						e--;
					}else {
						List<Integer> TargetSet = List.of(nums[i], nums[j], nums[s], nums[e]);
						if(!answer.contains(TargetSet)){
							answer.add(TargetSet);
						}
						s++;
						e--;
					}
				}

			}
		}
		return answer;
	}
}
