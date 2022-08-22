package leetcode;

// #38 - Count And Say

public class CountAndSay_38 {
	public static void main(String[] args) {
		int n = 5;
		System.out.println("countAndSay(n) = " + countAndSay(n));
	}

	// "1"
	// "11"
	// "21"
	// "1211"
	// "111221"
	public static String countAndSay(int n) {
		if(n == 1) return "1";

		String number = countAndSay(n-1);

		StringBuilder sb = new StringBuilder();
		int count = 0;
		char cur, nxt = '-';
		for(int i=0; i< number.length(); i++){
			count++;
			cur = number.charAt(i);
			if(i < number.length()-1) {
				nxt = number.charAt(i + 1);
			}

			if(i == number.length()-1 || cur != nxt){
				sb.append(count).append(cur);
				count = 0;
			}
		}

		return sb.toString();
	}
}
