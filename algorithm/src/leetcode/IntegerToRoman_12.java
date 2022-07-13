package leetcode;

// #12 - Integer To Roman
public class IntegerToRoman_12 {
	public static void main(String[] args) {
		int num = 3;
		System.out.println("intToRoman = " + intToRoman(num));
	}

	public static String intToRoman(int num) {
		int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] romanLetters = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

		StringBuilder answer = new StringBuilder();
		for(int i=0;i<values.length;i++) {
			while(num >= values[i]) {
				num = num - values[i];
				answer.append(romanLetters[i]);
			}
		}
		return answer.toString();
	}
}
