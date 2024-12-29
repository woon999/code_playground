package leetcode;

// #6 - ZigZag Conversion
import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion_6 {
	public static void main(String[] args) {
		String s = "PAYPALISHIRING";
		int numRows = 3;
		System.out.println("convert(s, numRows) = " + convert(s, numRows));
	}

	public static String convert(String s, int numRows) {
		int len = s.length();
		if(numRows == 1 || len == 0 || len == 1) return s;

		List<StringBuilder> builders = new ArrayList<>();
		for (int i = 0; i < numRows; i++) {
			builders.add(i, new StringBuilder());
		}

		boolean isRight = true;
		int idx = 0;
		int row = 0;
		while (idx < s.length()) {
			char word = s.charAt(idx);
			StringBuilder rowBuilder = builders.get(row);
			rowBuilder.append(word);
			if (isRight) {
				row++;
				if (row == numRows - 1) {
					isRight = false;
				}
			} else {
				row--;
				if (row == 0) {
					isRight = true;
				}
			}
			idx++;
		}

		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < builders.size(); i++) {
			answer.append(builders.get(i).toString());
		}
		return answer.toString();
	}
}
