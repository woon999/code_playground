package programmers.ttone;

// 그리디 #4 구명보트 
import java.util.*;

public class SaveBoat {

	public static void main(String[] args) {
		int[] people = {70, 50, 80, 50};
//		int[] people = {70, 30, 80, 20};
		int limit = 100;
		
		System.out.println(solution(people, limit));
	}
	
	public static int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        
        for(int i : people) {
        	System.out.println(i);
        }
        int idx=0;
        
        for(int j=people.length-1; j>=idx; j--) {
        	int sum =people[idx] + people[j];
        	
        	if(limit >= sum) {
        		System.out.println(sum);
        		answer ++;
        		idx++;
        	}else {
        		answer++;
        	}
        }
        
        return answer;
    }
}
