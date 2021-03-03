package programmers;

// binarysearch #2 징검다리 
import java.util.Arrays;

public class SteppingStone {
	public static void main(String[] args) {
		int distance = 25;
		int[] rocks = { 2, 14, 11, 21, 17 };
		int n =2;
		
		System.out.println(solution(distance, rocks, n));
	}
	
	public static int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        
        Arrays.sort(rocks);
        int left= 1;
        int right= distance;
        int mid = 0;
        
        while(left <= right) {
        	int cnt = 0;
        	int prev = 0;
        	
        	mid = (left+right)/2;
        	
        	for(int i=0; i<rocks.length; i++) {
        		int dis = rocks[i] - prev;
        		if(dis < mid) {
        			cnt ++;
        		}else {
        			prev = rocks[i];
 
        		}
        	}
        	System.out.println("prev:  " + prev + " ,  cnt : " + cnt);
        	
        	if(cnt <= n) {
        		answer = answer > mid ? answer : mid;
        		left = mid + 1;
        	}else {
        		right = mid -1;
        	}
        }
       
        return answer;
    }
	
	
}
