package leetcode;

// #349 - Intersection Of Two Arrays

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class IntersectionOfTwoArrays_349 {
	public static void main(String[] args) {
		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		System.out.println("intersection(nums1, nums2) = " + Arrays.toString(intersection(nums1, nums2)));
	}

	public static int[] intersection(int[] nums1, int[] nums2) {
		return Arrays.stream(nums2).filter(getSet(nums1)::contains).distinct().toArray();
	}

	private static Set<Integer> getSet(int[] nums){
		return Arrays.stream(nums).boxed().collect(Collectors.toSet());
	}
}
