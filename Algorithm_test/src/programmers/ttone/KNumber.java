package programmers.ttone;


// Sort #1 K번쨰수 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KNumber {

	public static void main(String[] args) {
		int[] array = {1, 5, 2, 6, 3, 7, 4};
		int[][] commands = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
		
		Solution6 s = new Solution6();
		
		System.out.println(Arrays.toString(s.solution(array, commands)));
	}
}

class Solution6{
	
	public int[] solution(int[] array, int[][] commands) {
		int size = commands.length;
        int[] answer = new int [size];
        
        // i ~j 까지 숫자 자르기
        List<Integer> list;
        
        for(int i = 0; i<size; i++) {
        	
        	list = new ArrayList<>();
        	
        	for(int j=commands[i][0]-1; j<commands[i][1]; j++) {
        		list.add(array[j]);
        	}
        	
        	list.sort((n1, n2) ->{ return n1- n2;});
        	

//        	System.out.println(i);
//        	for(int num : list) {
//            	System.out.print(num+" ");
//            }
//        	System.out.println();
        	
        	answer[i] = list.get(commands[i][2]-1);
        
        }
        
        
        
        return answer;
    }
	
}
