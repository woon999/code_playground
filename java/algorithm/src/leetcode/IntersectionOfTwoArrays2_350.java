package leetcode;

// #350 - Intersection Of Two Arrays 2
import java.util.*;

public class IntersectionOfTwoArrays2_350 {
	public static void main(String[] args) {
		int[] nums1 = {1,2};
		int[] nums2 = {2,1};
		System.out.println("intersect(nums1, nums2) = " + Arrays.toString(intersect(nums1, nums2)));
	}

	public static int[] intersect(int[] nums1, int[] nums2) {
		List<Integer> result = new ArrayList<>();
		Map<Integer, Integer> map = new HashMap<>();
		for(int e : nums1){
			map.put(e, map.getOrDefault(e, 0)+1);
		}

		for(int e : nums2){
			if(map.containsKey(e) && map.get(e) > 0){
				result.add(e);
				map.put(e, map.get(e)-1);
			}
		}
		return result.stream().mapToInt(i -> i).toArray();
	}
}
