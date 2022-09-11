package leetcode;

// #41 - First Missing Positive

public class FirstMissingPositive_41 {
	public static void main(String[] args) {
		int[] nums = {3,2,1};
		System.out.println("firstMissingPositive(nums) = " + firstMissingPositive(nums));
	}

	public static int firstMissingPositive(int[] nums) {
		int n = nums.length;
		int i = 0;
		while(i < n){
			if(nums[i] <= 0 || nums[i] >= n || nums[i] == i+1){
				i++;
				continue;
			}

			int tmp = nums[nums[i]-1]; // i가 들어가야할 곳
			if(tmp == nums[i]){ // 이미 nums[i] == i+1이면 패스
				i++;
				continue;
			}

			// nums[i] = i+1이 되게 위치 바꿔주기
			nums[nums[i]-1] = nums[i];
			nums[i] = tmp;
		}

		for(i=0; i<n; i++){
			if(nums[i] != i+1) return i+1;
		}

		return n+1;
	}
}
