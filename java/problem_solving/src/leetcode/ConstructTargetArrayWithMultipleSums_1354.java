package leetcode;

// #1354 Construct Target Array With Multiple Sums
import java.util.*;

public class ConstructTargetArrayWithMultipleSums_1354 {

	public static void main(String[] args) {
//		int[] target = {2, 900_000_002};
//		int[] target = {9, 3, 5};
//		int[] target = {1,1,10};
		int[] target = {8, 5};
		
		boolean x = isPossible(target);
		System.out.println("x = " + x);
		
	}
	
	public static boolean isPossible(int[] target) {
		Queue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
		int sum = Arrays.stream(target)
				.peek(pq::add)
				.sum();
		
		while(pq.peek() != 1) {
			int num = pq.poll();
			System.out.println("> " + num +", " + sum);
			sum -= num;
			
			// 1이 될 수 없는 경우 false 
			if(num <= sum || sum < 1) {
				System.out.println(num +", " + sum);
				return false;
			}
			
			System.out.println(">> " + num +", " + sum);
			// num -> num % sum 으로 점프 
			// num = 9, sum = 17-9=8 이고 [9, 3, 5] -> [(9%8)=1, 3, 5] 
			num %= sum;
			sum += num;
			
			if(num == 0) {
				num = sum;
			}
			pq.add(num);
			System.out.println(">>> " + num +", " + sum);
		}
		
		return true;
    }
}
