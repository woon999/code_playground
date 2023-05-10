package leetcode;

// #67 - Add Binary

public class AddBinary_67 {
	public static void main(String[] args) {
		String a = "11";
		String b = "1";
		System.out.println("addBinary(a, b) = " + addBinary(a, b));
	}

	public static String addBinary(String a, String b) {
		int aLen = a.length();
		int bLen = b.length();

		int len = Math.max(aLen, bLen);
		String ans = "";
		int s = 0, c = 0;

		for (int i = 0; i < len; i++) {
			int p = (i < aLen) ? (a.charAt(aLen - i - 1) - '0') : 0;
			int q = (i < bLen) ? (b.charAt(bLen - i - 1) - '0') : 0;

			s = p + q + c;
			c = s / 2;
			ans = s % 2 + ans;
		}

		return c == 1 ? "1" + ans : ans;
	}
}
