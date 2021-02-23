package programmers;

// 그리디 #1 체육복  
import java.util.Arrays;

public class TrainingSuit {

	public static void main(String[] args) {
		int n = 5;
		int[] lost = {3,1};
		int[] reserve = {2,3};
		
		System.out.println(solution(n, lost, reserve));
	}
	
	 public static int solution(int n, int[] lost, int[] reserve) {
	        int answer = 0;
	        boolean[] available = new boolean[n+2];
	        boolean[] check = new boolean[n+1];
	        
	        Arrays.fill(check, true);
	        
	        for(int i : lost) {
	        	check[i] = false;
	        }
	        for(int i=0; i<reserve.length; i++) {
	        	if(!check[reserve[i]]) {
	        		check[reserve[i]] = true;
	        	}else {
//	        		System.out.println(reserve[i]);
	        		available[reserve[i]] = true;
	        	}
	        		
	        }
	        
	        
	        for(int i=0; i<lost.length; i++) {
	        	
	        	// !check[lost[i]]를 안해주면 lost : 3,1 / reserve: 2,3에서 오류난다.
	        	// 대신에 lost를 정렬해줘도 됨 
	        	if(!check[lost[i]]) {
		        	if(available[lost[i]-1] || available[lost[i]+1]) {
		        		System.out.println(lost[i]);
		        		check[lost[i]] = true;
		        		if(available[lost[i]-1]){
		        			available[lost[i]-1]=false;
		        		}
		        		else if(available[lost[i]+1]){
		        			available[lost[i]+1]=false;
		        		}
		        	}
	        	}
	        }
	        for(boolean num : check) {
	        	System.out.println(num);
	        	if(num) answer++;
	        }
	        
	        
	        
	        
	        return answer-1;
	 }
}
