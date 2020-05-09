package kakao.winter2019;

import java.util.Arrays;

//	2019 카카오 개발자 겨울 인턴십 코딩테스트 #5 징검다리 건너기
//	[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

//	     입출력 예
//		stones							 k	result
//		[2, 4, 5, 3, 2, 1, 4, 2, 5, 1] 	 3	  3

public class Kakao05 {

	public static void main(String[] args) {
		int[] stones = {2, 4, 5, 3, 2, 1, 4, 2, 5, 1};
		
		System.out.println(solution(stones,3));
	}


	 public static int solution(int[] stones, int k) {
			 
	        int answer = Integer.MAX_VALUE;
	        
 			System.out.println(Arrays.toString(stones));
		 	for(int i =0; i<stones.length-k+1;) {
		 		int count =stones[i];
		 		int jump = 0;
		 		for(int j=i+1; j<i+k;j++) {
		 			if(stones[j]>=count) {
		 				count = stones[j];
		 				jump =j;
		 				System.out.println("j :" +j + ", count:" + count);
		 			}
		 			
		 		}
		 		
		 		if(jump==0) i++;
		 		else i = jump+1;
		 		
		 		answer = Math.min(count, answer);
		 		
		 		
		 	}
	        return answer;
	    }
	
}
