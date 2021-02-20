package programmers;

//Stack/Queue #3 기능 개발 

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 다른 사람 풀이
// stream, filter의 다양한 사용법 
//int[] dayOfend = new int[100];
//int day = -1;
//for(int i=0; i<progresses.length; i++) {
//    while(progresses[i] + (day*speeds[i]) < 100) {
//        day++;
//    }
//    dayOfend[day]++;
//}
//return Arrays.stream(dayOfend).filter(i -> i!=0).toArray();

public class Development {

	public static void main(String[] args) {
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		
		System.out.println(Arrays.toString(solution(progresses, speeds)));
	}
	
	public static int[] solution(int[] progresses, int[] speeds) {
        int len = progresses.length;
        Queue<Integer> q = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        // 작업 일 수 계산 후 저장 
        for(int i=0; i<len; i++) {
        	int work = 100 - progresses[i];
        	
        	int done = work/speeds[i];
        	if(work%speeds[i]>0) {
        		done++;
        	}
        	
        	// 완성된 기능 배포 개수 확인 후 queue clear 
        	if(!q.isEmpty() && q.peek() < done) {
        		list.add(q.size());
        		q.clear();
        	}
        
        	
        	q.add(done);
        	
        }
        
        list.add(q.size());
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}
