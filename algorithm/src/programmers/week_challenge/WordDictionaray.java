package programmers.week_challenge;

// week_challenge 모음사전 

import java.util.HashMap;
import java.util.Map;

public class WordDictionaray {

	public static void main(String[] args) {
		String word = "AAAAE";
		System.out.println(solution(word));
	}

	public static int solution(String word) {
		int answer = 0;
		char[] alpha = { 'A', 'E', 'I', 'O', 'U' };
		int[] numbers = new int[5];
		int total = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < 5; i++) {
			numbers[i] = (int) Math.pow(5, i+1);
			total += numbers[i];
			map.put(alpha[i], i);
		}

		for (int i = 0; i < word.length(); i++) {
			int wordIdx = map.get(word.charAt(i));
			answer += ((total / numbers[i]) * wordIdx) + 1;
		}
		
		return answer;
	}
}
