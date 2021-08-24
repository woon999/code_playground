package kakao.blind2018.first;

// blind #2 다트 게임 - 자료 구조, 스택   
import java.util.Stack;

public class DartGame {

	public static void main(String[] args) {
//		String dartResult = "10S2D*3T";
//		String dartResult = "1D2S#10S";
//		String dartResult = "1D2S0T";
		String dartResult = "1S*2T*3S";
//		String dartResult = "1D#2S*3S";
//		String dartResult = "1T2D3D#";
//		String dartResult = "1D2S3T*";
		
		System.out.println(solution(dartResult));
	}
	
	public static int solution(String dartResult) {
        int answer = 0;
        int len = dartResult.length();
        
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<len; i++	) {
        	String s = ""+dartResult.charAt(i);
        	
        	if(s.matches("^[0-9]")) {
        		String num = s;
        		if(i<len-1 && dartResult.charAt(i+1)=='0') {
        			num += dartResult.charAt(i+1);
        			i++;
        		}
        		stack.push(Integer.parseInt(num));
        	}
        	else if(s.matches("[D,T]")) {
        		if(!s.isEmpty()) {
        			int num = stack.pop();
        			if(s.equals("D")) {
        				num = (int) Math.pow(num, 2);
        			}else {
        				num = (int) Math.pow(num, 3);
        			}
        			stack.push(num);
        		}
        	}
        	else if(s.matches("[*]")) {
        		if(stack.size()==1) {
        			int num = stack.pop();
        			num *= 2;
        			stack.push(num);
        		}else {
        			int num1 = stack.pop();
        			int num2 = stack.pop();
        			num1 *= 2;
        			num2 *= 2;
        		
        			stack.push(num2);
        			stack.push(num1);
        		}
        	}
        	else if(s.matches("[#]")) {
        		if(!stack.isEmpty()) {
        			int num = stack.pop();
        			num *= -1;
        			stack.push(num);
        		}
        	}
        	
        }
        
        while(!stack.isEmpty()) {
        	answer += stack.pop();
        }
        
        return answer;
    }
}
