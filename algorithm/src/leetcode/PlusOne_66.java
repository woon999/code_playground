package leetcode;

// #66 - Plus One
import java.util.Arrays;

public class PlusOne_66 {
	public static void main(String[] args) {
		int[] digits = {9,9,9,9};

		System.out.println("plusOne(digits) = " + Arrays.toString(plusOne(digits)));
	}

	public static int[] plusOne(int[] digits) {
		digits[digits.length-1]++;
		int idx =  digits.length-1;
		while(idx > 0 && digits[idx] == 10){
			digits[idx]=0;
			digits[idx-1]++;
			idx--;
		}

		if(digits[0] < 10){
			return digits;
		}

		int[] answer = new int[digits.length+1];
		answer[0] = 1;
		return answer;
	}
}
