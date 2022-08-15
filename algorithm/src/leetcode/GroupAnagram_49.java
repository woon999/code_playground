package leetcode;

// #49 - Group Anagram

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagram_49 {
	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
		System.out.println("groupAnagrams(strs) = " + groupAnagrams(strs));
	}

	public static List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<>();
		Map<String, List<String>> map = new HashMap<>();

		for(int i=0; i<strs.length; i++){
			char[] cArr = strs[i].toCharArray();
			Arrays.sort(cArr);
			String key = String.valueOf(cArr);

			if(!map.containsKey(key)){
				List<String> list = new ArrayList<>();
				list.add(strs[i]);
				map.put(key, list);
			}else{
				map.get(key).add(strs[i]);
			}
		}

		for(List<String> value : map.values()){
			result.add(value);
		}
		return result;
	}
}
