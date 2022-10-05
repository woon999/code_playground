package leetcode;

// #3 - Longest Substring Without Repeating Characters
import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters_3 {
	public static void main(String[] args) {
		String s = "pwwkew";
		// String s = "dvdf";
		// System.out.println(lengthOfLongestSubstring(s));

		System.out.println(lengthOfLongestSubstring_improvement(s));
	}

	public static int lengthOfLongestSubstring(String s) {
		if(s.length() == 0) {
			return 0;
		}

		char[] arr = s.toCharArray();
		Set<Character> set = new HashSet<>();
		int max = 1;

		int[] dp = new int[arr.length];
		for(int i=0; i<arr.length; i++){
			dp[i] = 1;
			set.add(arr[i]);
			int idx = i+1;
			while(idx < arr.length){
				if(set.contains(arr[idx])) break;
				set.add(arr[idx]);
				dp[i]++;
				idx++;
			}

			set.clear();
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static int lengthOfLongestSubstring_improvement(String s) {
		Queue<Character> queue = new LinkedList<>();
		int max = 0;
		for(char c: s.toCharArray()){
			if(!queue.contains(c)){
				queue.offer(c);
			}else{
				queue.offer(c);

				while(queue.peek() != c){
					queue.poll();
				}
				queue.poll();
			}
			max = Math.max(max, queue.size());
		}
		return max;
	}
}
