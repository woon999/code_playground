package kakao.intern2020;

import java.util.ArrayList;
import java.util.HashMap;

//9	[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]]	[[8,5],[6,7],[4,1]]	true
//9	[[8,1],[0,1],[1,2],[0,7],[4,7],[0,3],[7,5],[3,6]]	[[4,1],[5,2]]	true
//9	[[0,1],[0,3],[0,7],[8,1],[3,6],[1,2],[4,7],[7,5]]	[[4,1],[8,7],[6,5]]	false

public class Kakao05 {

	public static void main(String[] args) {
		
		int[][] path = {{0,1},{0,3},{0,7},{8,1},{3,6},{1,2},{4,7},{7,5}};
		int[][] order = {{8,5},{6,7},{4,1}};
		
		solution(9,path,order);
	}
	
	public static boolean solution(int n, int[][] path, int[][] order) {
        boolean answer = true;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int i =0; i<path.length; i++) {
        	ArrayList<Integer> list = new ArrayList<>();
  
        	int value = 0;
        	
        	int val1 = path[i][0];
        	int val2 = path[i][1];
        	if(!map.containsKey(val1)) {
        		map.put(val1,path[i][1]);
        		value = path[i][1];
        		
        	}else if(!map.containsKey(val2)){
        		map.put(val2, path[i][0]);
        		value = path[i][0];
        	}
        	
        	
        	int parent = map.get(value);
        }
        return answer;
    }
}
