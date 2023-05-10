package leetcode;

// #191 - Number Of 1 Bits
public class NumberOf1Bits_191 {
	public static void main(String[] args) {
		int n = -3;
		System.out.println("hammingWeight(n) = " + hammingWeight(n));
	}

	public static int hammingWeight(int n) {
		int count = 0;
		for(int i=31; i>=0; i--){
			if((n&(1<<i)) != 0) {
				count++;
			}
		}
		return count;
	}
}
