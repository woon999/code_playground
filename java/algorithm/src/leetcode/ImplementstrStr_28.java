package leetcode;

// #28 - Implement strStr()

public class ImplementstrStr_28 {
	public static void main(String[] args) {
		// String haystack = "hello";
		// String needle = "ll";

		String haystack = "aaaaa";
		String needle = "bba";

		System.out.println("idx = " + strStr(haystack, needle));
	}

	public static int strStr(String haystack, String needle) {
		return haystack.indexOf(needle);
	}
}
