package leetcode;

// 136 - Single Number

// wow wow!!
// ref: https://leetcode.com/problems/single-number/discuss/1771771/Think-it-through-oror-Time%3A-O(n)-Space%3A-O(1)-oror-Python-Explained

public class SingleNumber_136 {
	public static void main(String[] args) {
		int[] nums = {4,1,2,1,2};
		System.out.println("singleNumber(nums) = " + singleNumber(nums));
	}

	public static int singleNumber(int[] nums) {
		int xor = 0;
		for(int num : nums){
			xor ^= num;
		}
		return xor;
	}
}
