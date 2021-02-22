package programmers;

// 완전탐색 #2 카펫 

import java.util.Arrays;

public class Carpet {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(10,2)));
	}
	
	public static int[] solution(int brown, int yellow) {
			int[] answer = new int[2];
	        int b= ((brown+4)/2);
	        int c = brown+yellow;
	        
	        int determinant = (b*b) - (4*c);
	       
//	        System.out.println(determinant);
//	        double x1 = (-b - Math.sqrt(determinant))/2;
//	        double x2 = (-b + Math.sqrt(determinant))/2;
//	       	System.out.println(x1+"," +x2);
	        	
	       	answer[0] = -(int)(-b - Math.sqrt(determinant))/2;
	        answer[1] = -(int)(-b + Math.sqrt(determinant))/2;
	        
	        return answer;
	}
}
