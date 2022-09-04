package leetcode;

// #125 - Valid Palindrome

public class ValidPalindrome_125 {
	public static void main(String[] args) {
		// String s = "A man, a plan, a canal: Panama";
		String s = "ab_a";
		System.out.println("isPalindrome(s) = " + isPalindrome(s));
	}

	public static boolean isPalindrome(String s) {
		s = s.replaceAll(" ", "").replaceAll("[^[a-z|A-Z|0-9]]", "").toLowerCase();
		System.out.println("s = " + s);

		StringBuilder sb = new StringBuilder(s);
		return sb.reverse().toString().equals(s);
	}
}
