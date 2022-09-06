package leetcode;

// #461 - Hamming Distance
public class HammingDistance_461 {
	public static void main(String[] args) {
		int x = 1;
		int y = 10;
		System.out.println("hammingDistance(x, y) = " + hammingDistance(x, y));
	}

	public static int hammingDistance(int x, int y) {
		int xor = x ^ y, count = 0;
		while (xor > 0) {
			xor &= (xor - 1);
			count++;
		}
		return count;
	}
}
