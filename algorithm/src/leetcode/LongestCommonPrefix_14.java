package leetcode;

// #14 - Longest Common Prefix
public class LongestCommonPrefix_14 {
	public static void main(String[] args) {
		String[] strs = {"flower", "flow", "flight"};
		System.out.println(longestCommonPrefix(strs));
	}

	public static String longestCommonPrefix(String[] strs) {
		String s = strs[0];
		for (int i = 0; i < s.length(); i++) {
			String pre = s.substring(0, s.length() - i);
			for (int j = 0; j < strs.length; j++) {
				if (!strs[j].startsWith(pre)) {
					break;
				}
				if (j == strs.length - 1) {
					return pre;
				}
			}
		}
		return "";
	}
}
