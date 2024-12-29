package leetcode;

// https://leetcode.com/problems/isomorphic-strings/

public class IsomorphicStrings_205 {

	public static void main(String[] args) {
		System.out.println("isIsomorphic(\"egg\", \"add\") = " + isIsomorphic("egg", "add")); // true
		System.out.println("isIsomorphic(\"foo\", \"bar\") = " + isIsomorphic("foo", "bar")); // false
		System.out.println("isIsomorphic(\"paper\", \"title\") = " + isIsomorphic("paper", "title")); // true
		System.out.println("isIsomorphic(\"badc\", \"baba\") = " + isIsomorphic("badc", "baba")); // false
	}

	public static boolean isIsomorphic(String s, String t) {
		int len = s.length();

		int[] m1 = new int[256];
		int[] m2 = new int[256];
		for (int i = 0; i < len; i++) {
			// badc (50-1), (49-2), (52-3) -> false
			// baba (50-1), (49-2), (49-3) -> false
			if (m1[s.charAt(i)] != m2[t.charAt(i)]) { // 0 0 이면 통과. 어느 하나가 이미 값이 있으면 false
				return false;
			}

			m1[s.charAt(i)] = i + 1;
			m2[t.charAt(i)] = i + 1;
		}

		return true;
	}
}
