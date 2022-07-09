package leetcode;

// #26 - Length Of Last Word

public class LengthOfLastWord_26 {
	public static void main(String[] args) {
		String s = "   fly me   to   the moon  ";

		System.out.println("s = " + lengthOfLastWord(s));
	}

	public static int lengthOfLastWord(String s) {
		String[] s1 = s.replaceAll("\\s+", " ").split(" ");
		return s1[s1.length-1].length();
	}
}
