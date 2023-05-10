package kakao.blind2020;

// blind #2 괄호 변환  - 문자열, 재귀
import java.util.Stack;

public class ParenthesisChange {

	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) {
//		String p = "(()())()";
//		String p = ")))(((()";
//		String p = ")(";
//		String p = "()))((()";
		String p = "))((";
		solution(p);
	}
	
	public static String solution(String p) {
        String answer = "";
        
        if(p.length()==0) return "";
        
        answer = solve(p,2);
        
        System.out.println(answer);
        return answer;
    
	}
	static String solve(String p, int r) {
		if(p.length()==0) return "";
		String divide = p.substring(0,r);
		while(!isBalanced(divide)) {
			r +=2;
			divide = p.substring(0,r);
		}
		String rest = p.substring(r, p.length());
		
//		System.out.println("step :" + divide +"," + rest);
		
		if(isPerfect(divide)) {
//			System.out.println("# :" + divide);
			return divide+solve(rest,2);
		}else {
//			System.out.println("## :" + divide+"," + rest);
			return "("+ solve(rest,2) + ")" +preProcessing(divide);
		}
	
	}
	
	// u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
	static String preProcessing(String str) {
		String s = "";
		for(int i=1; i<str.length()-1; i++) {
			char c = str.charAt(i);
			if(c=='(') {
				s+=')';
			}else {
				s+='(';
			}
		}
		return s;
	}
	static boolean isBalanced(String str) {
		int f1=0, f2=0;
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c=='(') {
				f1++;
			}else {
				f2++;
			}
		}
		
		if(f1==f2) return true;
		else return false;
		
	}

	static boolean isPerfect(String str) {
		Stack<Character> s = new Stack<>();
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c=='(') {
				s.push(c);
			}else {
				if(s.isEmpty()) {
					return false;
				}else {
					s.pop();
				}
			}
		}
		if(!s.isEmpty()) return false;
		return true;
	}
}
