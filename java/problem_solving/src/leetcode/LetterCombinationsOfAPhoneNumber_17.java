package leetcode;

// #17 - Letter Combinations Of A PhoneNumber
import java.util.*;

public class LetterCombinationsOfAPhoneNumber_17 {

	public static void main(String[] args) {
		String digit = "23";

		System.out.println("letterCombinations = " + letterCombinations(digit));
	}

	public static List<String> letterCombinations(String digits) {
		if(digits.isBlank()){
			return List.of();
		}

		Map<Character, List<String>> map = new HashMap<>();
		map.put('2', List.of("a", "b", "c"));
		map.put('3', List.of("d", "e", "f"));
		map.put('4', List.of("g", "h", "i"));
		map.put('5', List.of("j", "k", "l"));
		map.put('6', List.of("m", "n", "o"));
		map.put('7', List.of("p", "q", "r", "s"));
		map.put('8', List.of("t", "u", "v"));
		map.put('9', List.of("w", "x", "y", "z"));

		List<String> answer = new ArrayList<>();
		Queue<String> q = new LinkedList<>();
		q.add("");
		while(!q.isEmpty()){
			String cur = q.poll();

			if(cur.length() == digits.length()) {
				answer.add(cur);
				continue;
			}

			for(String alpha : map.get(digits.charAt(cur.length()))){
				q.add(cur + alpha);
			}
		}

		return answer;
	}
}
