package leetcode;

// #5 - Longest Palindromic Substring
public class LongestPalindromicSubstring_5 {

	public static void main(String[] args) {
		String s = "cbbd";
		System.out.println(longestPalindrome(s));
	}

	static int idx, len;
	public static String longestPalindrome(String s) {
		if(s.length() == 1){
			return s;
		}

		for (int i = 0; i < s.length() - 1; i++) {
			search(s, i, i);
			search(s, i, i + 1);
		}

		return s.substring(idx, idx + len);
	}

	public static void search(String s, int i, int j) {
		// <--i, j--> 팰린드롬 문자열 탐색
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}

		// j-i-1: 팰린드롬 길이
		if (len < j - i - 1) {
			idx = i + 1;
			len = j - i - 1;
		}
	}
}
