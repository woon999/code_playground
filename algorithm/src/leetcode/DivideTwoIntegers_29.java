package leetcode;

 // #29 - Divide Two Integers

public class DivideTwoIntegers_29 {
	public static void main(String[] args) {
		int dividend = 10;
		int divisor = -3;

		System.out.println("divide = " + divide(dividend, divisor));
	}
	public static int divide(int dividend, int divisor) {
		if(divisor == -1){
			if(dividend == Integer.MIN_VALUE){
				return Integer.MAX_VALUE;
			}else if(dividend == Integer.MAX_VALUE){
				return Integer.MIN_VALUE;
			}
		}
		return dividend/divisor;
	}
}
