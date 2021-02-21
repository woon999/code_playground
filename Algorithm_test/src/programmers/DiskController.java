package programmers;

// Heap #2 디스크 컨트롤러 
// SJF(Shortest Job First) 알고리즘 
import java.util.*;

public class DiskController {

	public static void main(String[] args) {
		int[][] jobs = {{0,3}, {2,6} , {1,9}};
		
		System.out.println(solution(jobs));
	}
	
	public static int solution(int[][] jobs) {
        int answer = 0;
        
        LinkedList<Task> list = new LinkedList<>();
        Queue<Task> q = new PriorityQueue<>(new Comparator<Task>() {
			@Override
			public int compare(Task o1, Task o2) {
				return o1.duration - o2.duration;
			}
        	
		});
        
        for(int[] i : jobs) {
        	list.add(new Task(i[0], i[1]));
        }
        list.sort((a,b) -> {return a.start-b.start;});
        
        
        int time = list.peek().start;
        int cnt =0;
        
        
        while(cnt < jobs.length) {
        	while(!list.isEmpty() && list.peek().start <= time) {
        		q.offer(list.poll());
        	}
        	
        	if(!q.isEmpty()) {
        		Task task = q.poll();
        		time += task.duration;
        		answer += time  - task.start;
        		cnt++;
        	}else {
        		time++;
        	}
        }
        
        return answer/cnt;
    }
}

class Task {
	int start;
	int duration;
	
	Task(int start, int duration){
		this.start = start;
		this.duration = duration;
	}
}