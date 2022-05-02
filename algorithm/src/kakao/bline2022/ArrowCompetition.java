package kakao.bline2022;

//#4 kakao2022blind 양궁 대회 - 브루트포스   

import java.util.*;

public class ArrowCompetition {

	public static void main(String[] args) {
//		int n = 5;
//		int[] info = {2,1,1,1,0,0,0,0,0,0,0};
//		int n =1;
//		int[] info = {1,0,0,0,0,0,0,0,0,0,0};
		
		int n =9;
		int[] info = {0,0,1,2,0,1,1,1,1,1,1};

//		int n =10;
//		int[] info = {0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3};

		System.out.println(Arrays.toString(solution(n, info)));
	}
	
	static int[] answer;
	static int[] g_info;
	static int max = -1;
	static List<Integer> ans;
	public static int[] solution(int n, int[] info) {
		g_info = info;
		answer = new int[11];
		Arrays.fill(answer, -1);
		ans = new ArrayList<>();
		
		int[] data = new int[11];
		simulation(0, n, data);
        
        if(max == -1) {
        	return new int[] {-1};
        }else {
        	return answer;
        }
	}
	
	static void simulation(int idx, int size, int[] data) {
		if(idx == 10){
			data[idx] = size;
        	int score = 0;
        	for(int i=0; i<11; i++) {
        		if(g_info[i] < data[i]) {  //내 점수가 더 높을 때 
        			score += (10-i);
        		}else if(g_info[i] > 0) {// 상대방 점수가 더 높을 때
        			score -= (10-i);
        		}
        	}
        	
        	if(score <= 0) return;
    		if(max <score) {
    			max = score;
    			answer = copyData(data);
    		}else if(max == score) {
    			if(comp(data)) {
    				answer = copyData(data);
    			}
    		}
        	
            return;
        }
         
        for(int i=0; i<=size; i++) {
        	data[idx] = i;
        	simulation(idx+1, size-i, data);
        }
	}
	
	static boolean comp(int[] a) {
		for(int i=10; i>=0; i--) {
			if(answer[i] != a[i]) return a[i]> answer[i];
		}
		return false;
	}
	
	static int[] copyData(int[] info) {
		int[] copy = new int[info.length];
		for(int i=0; i<info.length; i++) {
			copy[i] = info[i];
		}
		return copy;
	}
		
}
