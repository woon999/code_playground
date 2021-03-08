package programmers;

// 프로그래머스 그래프 #3 방의 개수
// 어렵다 다시 풀어보기 (자료구조 방식이 복잡함)
// 아래의 코드는 효율성이 그리 좋지 않음 
// String보다는 Map으로 좌표를 저장하고 boolean으로 경로 체크하는 방식으로 하는 것이 효율 더 좋음 
import java.util.*;

public class NumberOfRooms {

	static int[] dx = {0, 1, 1, 1, 0 , -1, -1, -1};
	static int[] dy = {1, 1, 0, -1, -1, -1, 0 ,1};
	
	public static void main(String[] args) {
		int[] arrows = {6,6,6, 4,4,4, 2,2,2, 0,0,0, 1,6,5, 5,3,6,0};
		
		solution(arrows);
	}
	
	public static int solution(int[] arrows) {
        int answer = 0;
        
        Set<String> line = new HashSet<>();
        Set<String> point = new HashSet<>();
        
        int x =0;
        int y = 0;
        
        point.add(x + " | " + y);
        
        for(int i=0; i<arrows.length; i++) {

        	// X 교차에서 발생하는 경우 때문에 이중포문 사용  
        	for(int j=0; j<2; j++) {
        		int vec = arrows[i];
        		String start = x + " | " + y;
        		
        		if(vec<=1 || vec==7) y++;
                if(1<=vec && vec<=3) x++;
                if(3<=vec && vec<=5) y--;
                if(5<=vec && vec<=7) x--;
                
                String pos = x + " | " +y;
                String normal = start + " | " + pos;
                String back = pos + " | " + start;
                		
//                System.out.println(pos);
//                System.out.println(normal);
//                System.out.println(back);
                
                if(point.contains(pos)) {
                	if(!line.contains(normal)) {
                		System.out.println(pos);
                		answer++;
                	}
                }
                
                
                point.add(pos);
                line.add(normal);
                line.add(back);
        	}
        }
        
        return answer;
    }
}

//class Position{
//	int x;
//	int y;
//	
//	Position(int x, int y){
//		this.x = x;
//		this.y = y;
//		
//	}
//}

