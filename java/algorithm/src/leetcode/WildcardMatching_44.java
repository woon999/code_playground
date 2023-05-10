package leetcode;

// #44 - Wildcard Matching
public class WildcardMatching_44 {
	public static void main(String[] args) {
		String s = "aab";
		String p = "c*a*b";

		System.out.println("isMatch(s, p) = " + isMatch(s, p));
	}

	public static boolean isMatch(String s, String p) {
		int strLen = s.length();
		int patLen = p.length();

		Boolean[][] dp = new Boolean[patLen][strLen];

		return isMatch(patLen-1, strLen-1, p, s, dp);
	}

	static boolean isMatch(int pIdx, int sIdx, String p, String s, Boolean[][] dp){
		if(pIdx < 0 && sIdx < 0) return true;
		if(pIdx < 0 && sIdx >= 0) return false;
		if(pIdx >= 0 && sIdx < 0){
			/*
				s = "" && pattern = "****" == true
				s = "" && pattern = "abc**" == false
			 */
			for(int i=0; i<=pIdx; i++){
				if(p.charAt(i) != '*'){
					return false;
				}
			}
			return true;
		}

		if(dp[pIdx][sIdx] != null){
			return dp[pIdx][sIdx];
		}

		if(p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx)){
			return dp[pIdx][sIdx] = isMatch(pIdx-1, sIdx-1, p, s, dp);
		}

		if(p.charAt(pIdx) == '*'){
			return dp[pIdx][sIdx] = isMatch(pIdx-1, sIdx, p, s, dp) || isMatch(pIdx, sIdx-1, p, s, dp);
		}

		return false;
	}
}
