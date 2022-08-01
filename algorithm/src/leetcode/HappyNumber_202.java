package leetcode;

// #202 - Happy Number
import java.util.HashSet;
import java.util.Set;

public class HappyNumber_202 {
	public static void main(String[] args) {
		int n = 2;
		System.out.println("isHappy(n) = " + isHappy(n));
	}

	public static boolean isHappy(int n) {
		Set<Integer> set = new HashSet<>();
		while(true){
			if(n==1) break;
			n = calc(n);
			if(!set.add(n)){ // infinite loop
				return false;
			}
		}
		return true;
	}

	public static int calc(int n){
		int sum = 0;
		while(n!=0){
			sum += Math.pow(n%10,2);
			n /= 10;
		}
		return sum;
	}
}
