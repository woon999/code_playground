package leetcode;

// #88 - Merge Sorted Array

public class MergeSortedArray_88 {
	public static void main(String[] args) {
		// int[] nums1 = {1,2,3,0,0,0};
		// int[] nums2 = {2,5,6};
		// int m = 3;
		// int n = 3;
		int[] nums1 = {0};
		int[] nums2 = {1};
		int m = 0;
		int n = 1;
		merge(nums1, m, nums2, n);
	}

	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int n1 = m-1;
		int n2 = n-1;
		int len = n+m;
		for (int i = len-1; i >= 0; i--) {
			if(n1 < 0 || (n2 >= 0 && nums1[n1] < nums2[n2])){
				nums1[i] = nums2[n2--];
			}else{
				nums1[i] = nums1[n1--];
			}
		}
	}
}
