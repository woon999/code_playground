package leetcode;

// #9 Palindrome Number
public class PalindromeNumber_9 {

	public static void main(String[] args) {
		int x = 10;
		System.out.println(isPalindrome(x));
	}

	public static boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}

		String xs = x+"";
		int s = 0;
		int e = xs.length()-1;

		while (s < e) {
			if(xs.charAt(s) != xs.charAt(e)){
				return false;
			}
			s++;
			e--;
		}

		return true;
	}

}
