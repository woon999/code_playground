package programmers.ttone;

// Heap #1 더 맵게 
import java.util.PriorityQueue;
import java.util.Queue;

public class Hotter {
	public static void main(String[] args) {
		int[] scoville = {1, 2, 10, 9, 3, 12};
//		int[] scoville = {1, 1, 1};
		
		System.out.println(solution(scoville, 100));
	}

	public static int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> q = new PriorityQueue<>();
        
        for(int i : scoville) {
        	q.add(i);
        }
        
        while(q.peek() < K) {
        	if(q.size()==1) {
        		return -1;
        	}
        	int one = q.poll();
        	int two = q.poll();
        	int k = one + two*2;
        	q.offer(k);
        	
//        	System.out.println(q.peek());
        	answer++;
        	
        }
        
        return answer;
    }
	
}
