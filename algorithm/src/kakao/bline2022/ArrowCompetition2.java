package kakao.bline2022;

//#4 kakao2022blind 양궁 대회 - 비트마스크 

import java.util.*;

public class ArrowCompetition2 {

	public static void main(String[] args) {
//		int n = 5;
//		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
//		int n =1;
//		int[] info = {1,0,0,0,0,0,0,0,0,0,0};
		
//		int n =9;
//		int[] info = {0,0,1,2,0,1,1,1,1,1,1};

		int n =10;
		int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};

		System.out.println(Arrays.toString(solution(n, info)));
	}
	
	// 그리디
	// 가장 큰 점수 10점부터 차례대로 +1 만큼 할당하기
	public static int[] solution(int n, int[] info) {
		int[] answer = new int[11];
		int max = -1;
		
		int size = 1<<10;
		for(int c=0; c<size; c++) {
			int[] data = new int[11];
			int arrows = n;
			int score = 0;
			for(int i=0; i<11; i++) {
				if((c & (1<<i)) != 0) {
					score += (10-i); // 점수 획득
					int needArrows = info[i] + 1;
					data[i] = needArrows; 
					arrows -= needArrows;  
				}else if(info[i] != 0) { // 상대 점수가 더 높은 경우 
					score -= (10-i);
				}
			}
			
			// 점수가 낮거나 화살이 부족한 경우 
			if(score <= 0 || arrows < 0) continue;
			data[10] += arrows; // 남은 화살은 마지막에 추가  
			if(max <score) {
    			max = score;
    			answer = Arrays.copyOf(data, 11);
    		}else if(max == score) {
    			if(comp(answer, data)) {
    				answer = Arrays.copyOf(data, 11);
    			}
    		}
		}
        
        if(max == -1) {
        	return new int[] {-1};
        }else {
        	return answer;
        }
	}
	
	static boolean comp(int[] answer, int[] comp) {
		for(int i=10; i>=0; i--) {
			if(answer[i] != comp[i]) return comp[i]> answer[i];
		}
		return false;
	}
}
