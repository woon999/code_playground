package leetcode;

// #171 - Excel Sheet Column Number

public class ExcelSheetColumnNumber_171 {
	public static void main(String[] args) {
		String columnTitle = "FXSHRXW";
		System.out.println("titleToNumber(columnTitle) = " + titleToNumber(columnTitle));
	}

	public static int titleToNumber(String columnTitle) {
		char[] cArr = columnTitle.toCharArray();
		int result = 0;
		for (int i = 0; i < cArr.length; i++) {
			char c = cArr[i];
			result += getPow(cArr.length, i) *alphaToInt(c);
		}
		return result;
	}

	static int getPow(int len, int i) {
		return (int)Math.pow(26, len - i - 1);
	}

	static int alphaToInt(char c) {
		return c - 64;
	}
}
