package leetcode;

// #30 - Substring With Concatenation Of All Words
import java.util.*;

public class SubstringWithConcatenationOfAllWords_30 {
	public static void main(String[] args) {
		String s = "barfoothefoobarman";

		String[] words = {"foo", "bar"};

		System.out.println("findSubString(s, words) = " + findSubstring(s, words));
	}

	public static List<Integer> findSubstring(String s, String[] words) {
		Map<String, Integer> map = new HashMap<>();

		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}

		List<Integer> result = new ArrayList<>();
		int oneWordLength = words[0].length();
		int wordsLength = words.length;

		for (int i = 0; i <= s.length() - (oneWordLength * wordsLength); i++) {
			Map<String, Integer> wordSeen = new HashMap<>();
			// i: start, j: word count
			for (int j = 0; j < wordsLength; j++) {
				int idx = i + j * oneWordLength;
				String nextWord = s.substring(idx, idx + oneWordLength);
				if (!map.containsKey(nextWord))
					break;

				wordSeen.put(nextWord, wordSeen.getOrDefault(nextWord, 0) + 1);

				if (wordSeen.get(nextWord) > map.getOrDefault(nextWord, 0))
					break;

				if (j + 1 == wordsLength) { // match all word
					result.add(i);
				}
			}
		}

		return result;
	}
}
