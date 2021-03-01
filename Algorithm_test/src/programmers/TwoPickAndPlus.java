package programmers;

// programmers 두개 뽑아서 더하기 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPickAndPlus {

	public static void main(String[] args) {
		int[] numbers = { 2,1,3,4,1};
		
		System.out.println(Arrays.toString(solution(numbers)));
	}
	
	public static int[] solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        
        for(int i=0; i<numbers.length; i++) {
        	for(int j= i+1; j<numbers.length; j++) {
        		int c = numbers[i] + numbers[j];
        		if(!list.contains(c)) list.add(c);
        	}
        	
        }
        
        int[] answer = new int[list.size()];
        
        for(int i=0; i <list.size(); i++) {
        	answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
}
