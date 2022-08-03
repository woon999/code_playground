package leetcode;

// 43 - Multiply Strings

public class Multiply_Strings_43 {
	public static void main(String[] args) {
		// String num1 = "123";
		// String num2 = "456";

		String num1 = "9133";
		String num2 = "0";

		System.out.println("multiply(num1, num2) = " + multiply(num1, num2));
	}

	public static String multiply(String num1, String num2) {
		char[] c1 = num1.toCharArray();
		char[] c2 = num2.toCharArray();

		int[] result = new int[c1.length + c2.length];
		for(int i = c2.length-1; i>=0; i--){
			for(int j = c1.length-1; j>=0; j--){
				int p1 = i+j+1; // 올림수
				int p2 = i+j;

				int v = (c2[i] - '0')*(c1[j] - '0');

				if(v >= 10){
					result[p1] += v%10;
					result[p2] += v/10;
					ceil(result, p1, p2);
				}else {
					result[p1] += v;
					ceil(result, p1, p2);
				}
			}
		}

		boolean isZero = true;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<result.length; i++){
			if(result[i]!=0) isZero =false;
			if(!isZero) sb.append(result[i]);
		}

		String mul = sb.toString();
		return mul.isBlank()? "0" : mul;
	}

	private static void ceil(int[] result, int p1, int p2) {
		if (result[p1] >= 10) {
			result[p2] += result[p1] / 10;
			result[p1] %= 10;
		}
	}
}
