package kakao.intern2020;

// intern #1 키패드 누르기 
import java.util.ArrayList;
import java.util.List;

public class KeyPad {

	static int left_pos=4, right_pos=4;
	static boolean right_flag = false;// mid에 손가락이 가있냐
	static boolean left_flag = false;// mid에 손가락이 가있냐
	static boolean type = false;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
//		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
//		String hand = "right";
		int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
		String hand = "left";

		
		System.out.println(solution(numbers, hand));
	}
	
	  public static String solution(int[] numbers, String hand) {
	        if(hand.equals("right")) type = true; // 왼,오른손 잡이 
	        
	        List<Integer> left = new ArrayList<>();
	        left.add(1);
	        left.add(4);
	        left.add(7);
	        
	        List<Integer> right = new ArrayList<>();
	        right.add(3);
	        right.add(6);
	        right.add(9);
	        
	        List<Integer> mid = new ArrayList<>();
	        mid.add(2);
	        mid.add(5);
	        mid.add(8);
	        mid.add(0);
	        
	        int mid_pos =0;
	        
	        for(int i=0; i<numbers.length; i++) {
	        	
	        	int key = numbers[i];
	        	
	        	if(left.contains(key)) {
	        		
	        		for(int floor=0; floor<left.size(); floor++) {
	        			if(left.get(floor) == key) {
	        				left_pos = floor+1;
	        				left_flag = false;
	        			}
	        		}
//	        		System.out.println(key+" -> left :"+left_pos);
	        		sb.append("L");
	        		
	        	}
	        	
	        	else if(right.contains(key)) {
	        		for(int floor=0; floor<right.size(); floor++) {
	        			if(right.get(floor) == key) {
	        				right_pos = floor+1;
	        				right_flag = false;
	        			}
	        		}
//	        		System.out.println(key+" -> right :"+right_pos);
	        		sb.append("R");
	        	}
	        	else {
	        		for(int floor=0; floor<mid.size(); floor++) {
	        			if(mid.get(floor) == key) {
	        				mid_pos = floor +1;
	        			}
	        		}
	        		mid_move(left_pos, right_pos, mid_pos);
//	        		System.out.println(key+" -> left :"+left_pos + ", right :"+right_pos);
	        		
	        	}
	        }
	        return sb.toString();
	    }
	  
	  
	  // 가운데 숫자일 경우 
	  static void mid_move(int left, int right, int mid) {
		  	int left_dis = Math.abs(left-mid)+1;
  			int right_dis = Math.abs(right-mid)+1;
  			
  			// 이미 오른이 mid 
  			if(right_flag) {
  				right_dis-=1;
  			}
  			//이미 왼이 mid 
  			if(left_flag) {
  				left_dis-=1;
  			}
//  			System.out.println(left_dis +"vs" + right_dis);
  			
  			if(left_dis <right_dis) {
  				left_pos = mid;
  				left_flag = true;
  				sb.append("L");
    		}else if(left_dis > right_dis) {
    			right_pos =mid;
    			right_flag = true;
    			sb.append("R");
    		}else {
    			if(type) {
    				right_pos =mid;
        			right_flag = true;
        			sb.append("R");
    			}else {
    				left_pos = mid;
      				left_flag = true;
      				sb.append("L");
    			}
    		}
		  
	  }
}
