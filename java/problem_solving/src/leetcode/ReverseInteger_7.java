package leetcode;

// #7 - Reverse Integer

public class ReverseInteger_7 {
	public static void main(String[] args) {
		int x = 10900;
		System.out.println(reverse(x));
	}

	public static int reverse(int x) {
		if(x == 0){
			return x;
		}

		boolean isNegative = false;
		int reverseNum = 0;
		try{
			System.out.println("x = " + x);
			if(x < 0){
				isNegative = true;
				x *= -1;
			}

			reverseNum = Integer.parseInt(new StringBuilder(""+x).reverse().toString().replace("^0+", ""));
		}catch (NumberFormatException e){
			reverseNum = 0;
		}

		if(isNegative){
			return -1 * reverseNum;
		}

		return reverseNum;
	}
}
