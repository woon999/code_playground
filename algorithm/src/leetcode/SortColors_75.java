package leetcode;

// #75 - Sort Colors
import java.util.Arrays;
import java.util.List;

public class SortColors_75 {
	public static void main(String[] args) {
		int[] nums = {2,0,2,1,1,0};

		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

	public static void sortColors(int[] nums) {
		int zero =0, one=0, two=0;
		for(int i=0; i<nums.length; i++){
			if(nums[i] == 0){
				zero++;
			}else if(nums[i] == 1){
				one++;
			}else {
				two++;
			}
		}

		List<Integer> list = List.of(zero, one, two);
		int idx = 0;
		for(int i=0; i<list.size(); i++){
			int size = list.get(i) + idx;
			for(; idx<size; idx++){
				nums[idx] = i;
			}
		}
	}
}
