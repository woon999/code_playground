package programmers.ttone;

//Stack/Queue #4 프린터 
import java.util.*;


//  PriorityQueue 사용 예제

//	int answer = 1;
//	PriorityQueue priority = new PriorityQueue<>(Collections.reverseOrder());
//	
//	for(int task : priorities){
//	    priority.add(task);
//	    System.out.println(priority);
//	}
//	//{2,5,4,1,3};
//	
//	System.out.println(priority);
//	while(!priority.isEmpty()){
//	    for(int i=0; i<priorities.length; i++){
//	        if(priorities[i] == (int)priority.peek()) {
//	            if(i == location){
//	                return answer;
//	            }
//	            priority.poll();
//	            answer++;
//	        }
//	    }
//}


public class Printer {

	public static void main(String[] args) {
		int[] priorities = {1, 1, 9, 1, 1, 1};
//		int[] priorities = {2, 1, 3 ,2};
		int location = 0;
		
		System.out.println(solution(priorities, location));
	}
	
	public static int solution(int[] priorities, int location) {
        int answer = 0, idx =0;
        int size = priorities.length;
        
        boolean flag = false;
        Queue<Print> q = new LinkedList<>();
        for(int i : priorities) {
        	q.add(new Print(idx++, i));
        }
        
        while(!q.isEmpty()) {
        	flag = false;
        	int front = q.peek().priority;
        	
        	for(Print p : q) {
        		if(p.priority > front) flag = true;
        	}
        	
        	if(flag) {
        		q.add(q.poll());
        	}
        	else { // 맨 앞의 우선순위가 가장 높을 때
        		if(q.poll().index == location) {
        			answer = size - q.size();
        		}
        	}
        	
        }
        
        return answer;
    }
}

class Print{
	int index;
	int priority;
	
	Print(int index, int priority){
		this.index = index;
		this.priority =priority;
	}
}
