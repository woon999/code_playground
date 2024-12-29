package leetcode;

// #13 - Roman To Integer
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger_13 {
	public static void main(String[] args) {
		String s = "III";
		System.out.println(romanToInt(s));
	}

	public static int romanToInt(String s) {
		Map<Character, Integer> map = romans();

		int result = 0;
		int num = 0, nextNum = 0;
		for (int i = 0; i < s.length(); i++) {
			num = map.get(s.charAt(i));

			if (i+1 < s.length()) {
				nextNum = map.get(s.charAt(i + 1));
			}

			if (nextNum != 0 && num >= nextNum) {
				result += num;
			} else {
				if (nextNum == 0) {
					return result + num;
				}
				result -= num;
			}
			nextNum = 0;
		}
		return -1;
	}

	private static Map<Character, Integer> romans() {
		Map<Character, Integer> map = new HashMap<>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1_000);

		return map;
	}
}
