package leetcode;

// #10 - Regular Expression Matching
import java.util.regex.Pattern;

public class RegularExpressionMatching_10 {
	public static void main(String[] args) {
		String s = "aa";
		String p = ".*";

		System.out.println("isMatch(s, p) = " + isMatch(s, p));
	}

	public static boolean isMatch(String s, String p) {
		return Pattern.compile(p).matcher(s).matches();
	}
}
