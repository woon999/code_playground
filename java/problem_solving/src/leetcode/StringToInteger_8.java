package leetcode;

// #8 - String To Integer (atoi)

public class StringToInteger_8 {
	public static void main(String[] args) {
		// String s = "word   -42   ";
		String s = "42";
		System.out.println("s = " + myAtoi(s));
	}

	public static int myAtoi(String s) {
		System.out.println("s = " + s);

		if(s.length() == 0){
			return 0;
		}

		s = s.trim();

		System.out.println("s = " + s);
		boolean isNegative = false;
		int idx = 0;
		if(s.startsWith("-")){
			isNegative = true;
			idx++;
		}else if(s.startsWith("+")) {
			idx++;
		}


		double number = 0;
		while(idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9'){
			number = number*10 + (s.charAt(idx++) -'0');
			System.out.println("number = " + number);
		}
		System.out.println("number = " + number);



		if(isNegative){
			number = -number;
		}

		if(number < Integer.MIN_VALUE){
			return  Integer.MIN_VALUE;
		}else if(number > Integer.MAX_VALUE){
			return  Integer.MAX_VALUE;
		}

		return (int)number;
	}
}
