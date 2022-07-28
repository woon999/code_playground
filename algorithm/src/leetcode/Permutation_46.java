package leetcode;

// #46 - Permutation

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Permutation_46 {
	public static void main(String[] args) {
		int[] nums = {1,2,3};

		System.out.println("permute(nums) = " + permute(nums));
	}

	public static List<List<Integer>> permute(int[] nums) {
		return permutation(nums.length, new Stack<>(), new boolean[nums.length], nums, new ArrayList<>());
	}

	static List<List<Integer>> permutation(int n, Stack<Integer> st, boolean[] check, int[] nums, List<List<Integer>> result) {
		if(st.size() == n){
			List<Integer> perm = new ArrayList<>();
			for(int i : st){
				perm.add(i);
			}
			result.add(perm);
			return result;
		}

		for(int i=0; i<n; i++){
			if(!check[i]){
				check[i] = true;
				st.push(nums[i]);
				permutation(n, st, check, nums, result);
				st.pop();
				check[i] = false;
			}
		}

		return result;
	}
}
