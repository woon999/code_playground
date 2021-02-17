package programmers;

// Hash #3
import java.util.HashMap;
import java.util.Map;

public class Spy {

	public static void main(String[] args) {
		Solution4 s = new Solution4();
		
		String[][] clothes = {
				{"glass", "face"},
				{"sungalss", "face"}, 
				{"blue_tshirt", "up"},
				{"jean", "down"},
				{"coat", "outer"},
				
		};
		
		System.out.println(s.solution(clothes));
	}
}

/*
 * 2
 * 1
 * 1
 * 1
 * 
 * 0 1 2 
 * 0 1
 * 0 1
 * 0 1
 * - (0000)
 * 
 * 3 2 2 2 -1 = 23 
 */

class Solution4 {
    public int solution(String[][] clothes) {
        int answer = 0;
        int len = clothes.length;
        
        Map<String, Integer> kinds = new HashMap<>();
        
        for(int i=0; i< len; i++) {
        	
        	kinds.put(clothes[i][1], kinds.getOrDefault(clothes[i][1], 0)+1);
        	
        }
        
        int cnt = 1;
        for(String key : kinds.keySet()) {
        	
        	cnt *= kinds.get(key)+1;
//        	System.out.println(kinds.get(key));
        }
        
        if(cnt != len) {
        	answer = cnt-1;
        }else {
        	answer = len;
        }
        
        return answer;
    }
}