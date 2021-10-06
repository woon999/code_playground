package programmers.ttone;

// 완전탐색 #1 모의고사 

import java.util.*;
import java.util.Map.Entry;

public class PracticeTest {

	public static void main(String[] args) {
//		int[] answers = { 1,2,3,4,5};
//		int[] answers = { 1,3,2,4,2};
		int[] answers = { 3,1,1,3,5};
		
		System.out.println(Arrays.toString(solution(answers)));
	}
	
	// 1: 12345
	// 2: 21232425
	// 3: 3311224455
	public static int[] solution(int[] answers) {
        int[] man1 = {1, 2, 3, 4, 5};
        int[] man2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] man3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        Map<Integer, Integer> score = new HashMap<>();
        score.put(1, 0);
        score.put(2, 0);
        score.put(3, 0);
        
        for(int i=0; i<answers.length; i++) {
        	int num = answers[i];
        	
        	if(num == man1[i%5]) {
        		score.put(1, score.get(1)+1);
        	}
        	
        	if(num == man2[i%8]) {
        		score.put(2, score.get(2)+1);
        	}
        	
        	if(num == man3[i%10]) {
        		score.put(3, score.get(3)+1);
        	}
        }

        
        int max =0;
        for(int i=1; i<4; i++) {
        	if(score.get(i) > max) max = score.get(i);
        }
        
        List<Integer> answerList = new ArrayList<>();
        for(int i=1; i<4; i++) {
        	if(max == score.get(i)) answerList.add(i);
        }
        
        int[] answer = new int[answerList.size()];
        for(int i =0; i<answerList.size(); i++) {
        	answer[i] = answerList.get(i);
        }
        
//        System.out.println(score.entrySet());
//		  entry : Map저장된 데이터를 열거해주는 데이터를 Set으로 반환해주는 인터페이스 
//        그런데, key 순서 정렬이되어서 테스트코드 3개 통과못함  
        
//        List<Entry<Integer,Integer>> answerList = new ArrayList<>(score.entrySet());
//        answerList.sort((a,b) -> {return b.getValue()- a.getValue();});
//
//        List<Integer> answer = new ArrayList<>();
//        for(Entry<Integer,Integer> i : answerList) {
//        	System.out.println(answerList.get(0) + " , " + i);
//        	if(answerList.get(0).getValue() == i.getValue()) {
//        		answer.add(i.getKey());
//        		System.out.println(i.getKey());
//        	}
//        }
        
        
        // stream 간단하지만 시간 너무 많이 잡아먹음 
//        return answer.stream().mapToInt(i -> i.intValue()).toArray();
        return answer;
    }
}
