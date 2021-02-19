package programmers;

// Sort #3 H-Index 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HIndex {
	
//	int max = 0;
//    for(int i = citations.length-1; i > -1; i--){
//        int min = (int)Math.min(citations[i], citations.length - i);
//        System.out.println(citations[i] + " , " + (citations.length)+"-" +i);
//        if(max < min) max = min;
//    }
//    
//    return max;

	public static void main(String[] args) {
		
//		int[] citations = {3, 0, 6, 1, 5};
		int[] citations = {2,2,2,2};
		
		System.out.println(solution(citations));
		
	}
	
	public static int solution(int[] citations) {
       int len = citations.length;
       int sum=0;
       for(int i : citations) {
    	   sum += i;
       }
       if(sum==0) {
    	   return 0;
       }
        
        Arrays.sort(citations);
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<len; i++) {
        	int pos = 0;	
        	int count =0;
        	
        	while(pos <len) {
        		if(i+1 <= citations[pos]) {
        			count++;
        		}
        		pos++;
        	}
        	if(i+1 <= count) {
        		list.add(i+1);
        	}
        }
        
        Collections.sort(list, Collections.reverseOrder());
        return list.get(0);
    }
}
