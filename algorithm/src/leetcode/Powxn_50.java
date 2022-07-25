package leetcode;

// #50 - Pow(x,n)

public class Powxn_50 {
	public static void main(String[] args) {
		double x = 2.00;
		int n = 10;
		System.out.println("myPow(x, n) = " + myPow(x, n));
	}

	public static double myPow(double x, int n) {
		// return Math.pow(x, n);

		double ans = 1, pow;
		if (n == 0) {
			return 1;
		} else if (n < 0) {
			pow = myPow(x, n / 2);
			ans = pow * pow;
			if (n % 2 != 0) {
				ans /= x;
			}
		} else {
			if (n == 1) {
				ans = x;
			} else {
				pow = myPow(x, n / 2);
				ans = pow * pow;
				if (n % 2 != 0) {
					ans *= x;
				}
			}
		}
		return ans;
	}
}
