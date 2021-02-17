package programmers;



public class NjinsuGame {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		System.out.println(s.solution(16, 16, 2, 1));
		
	}
}


class Solution {
    public String solution(int n, int t, int m, int p) {
        // n진법
        // 미리 구할 숫자 개수 t개
        // 게임에 참가하는 인원 m
        // 튜브의 순서 p
        
        StringBuilder sb = new StringBuilder();
        sb.append("0");
        
        int idx =1;

        while(true){
        	
        	if(sb.length() > t*m + p) break;
        	
        	sb.append(Boxing(idx,n));
        	
        	idx ++;
        }
        
//        System.out.println(sb.toString());
        char[] out = sb.toString().toCharArray();
        
        sb.delete(0, sb.length());
        		
        for(int i = p-1; i< t*m +p-1; i+= m ) {
        	sb.append(out[i]);
        }
        
      
        return sb.toString();
    }
    
    public String Boxing(int idx, int n) {
    	
    	StringBuilder tmp = new StringBuilder();
    	
    	while(true) {
    		
    		tmp.append(getNum(idx%n));
    		idx /= n;
    		
    		if(idx == 0) break;
    	}
    
    	return tmp.reverse().toString();
    	
    }
    
    public String getNum(int num) {
    	
    	switch(num){
			case 10: 
				return "A";
			case 11: 
				return "B";
			case 12: 
				return "C";
			case 13: 
				return "D";
			case 14: 
				return "E";
			case 15: 
				return "F";
				
			default:
				return num+ "";
    	}
    	
    	
    }

    
}