package programmers.ttone;

import java.util.Arrays;

// Binary Search #1 입국심사 

public class Immigration {

	public static void main(String[] args) {
		int n = 6;
		int[] times = {7,10};
		
		System.out.println(solution(n,times));
	}
	
	public static long solution(int n, int[] times) {
        long answer = 0;
       
       Arrays.sort(times);
        long left = 1;
        long right = n * (long)times[times.length-1];
        long done = 0;
        
        while(left <= right ) {
        	long mid = (left+right)/2;
        	
        	done =0;
        	
        	for(int time : times) {
        		done += mid/time;
        	}
        	
        	if(done < n) {
        		left = mid +1;
        	}else {
        		right = mid-1;
        		answer = mid;
        	}
        	
        }
        return answer;
    }
}
