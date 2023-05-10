package kakao.intern2020;

//intern #2 수식 최대화 (순열조합, ArrayList 삽입/삭제) 
import java.util.*;

public class MaxmizeExpression {
	
	static Long answer = Long.MIN_VALUE; 
	static boolean[] check = new boolean[3];
	public static String[] oper = {"+", "-", "*"};
	static List<String> operList = new ArrayList<>();
	static List<Long> numList = new ArrayList<>();

	public static void main(String[] args) {
//		String expression = "50*6-3*2";
		String expression = "100-200*300-500+20";
		System.out.println(solution(expression));
	}
	
	public static long solution(String expression) {
       
        for(int i=0; i<expression.length(); i++) {
        	char oper =expression.charAt(i);
        	if(oper =='-' ||oper == '*' ||oper == '+') {
        		operList.add(""+oper);
        	}
        }
        
        String[] exp = expression.split("[-*+]");
        
        for(String s : exp) {
        	Long num = Long.parseLong(s);
        	numList.add(num);
        }
        
//        for(long num : numList) {
//        	System.out.print(num+" ");
//        }
//        System.out.println();
        
        permutation("", 0);
        
        return answer;
    }
	
	static void permutation(String order, int depth) {
		if(depth == 3) {
			System.out.println(order);
			
			solve(order);
			return;
		}

		for(int i=0; i<oper.length; i++) {
			if(!check[i]) {
				check[i] = true;
				permutation(order+oper[i], depth+1);
				check[i] = false;
			}
		}
		
		
		
	}
	
	// 연산 순위에 맞춰 계산 
	static void solve(String oper_order) {
		List<String> t_operList = new ArrayList<>();
		List<Long> t_numList = new ArrayList<>();
		
		t_operList.addAll(operList);
		t_numList.addAll(numList);
		
		String[] t_ordrer = oper_order.split("");

		// 연산자 우선순위대로 실행 
		for(int i = 0; i < t_ordrer.length; i++) {
			String op = t_ordrer[i]; 
			
			for(int j = 0; j < t_operList.size(); j++) {
				
				// 연산자 일치할 경우 연산 실행 
				if(t_operList.get(j).equals(op)) { 
					long n1 = t_numList.get(j);
					long n2 = t_numList.get(j+1);
					
					System.out.println(op+ ": -> " +n1+" ," + n2);
					
					// 값 계산 
					long res = cal(n1, n2, op);
					
					
					// 값 삭제 및 추가 
					t_numList.remove(j+1);
					t_numList.remove(j);
					t_operList.remove(j);
					
					t_numList.add(j--, res);
				}
			}
			
		}
		System.out.println(Math.abs(t_numList.get(0)));
		answer = Math.max(answer, Math.abs(t_numList.get(0)));
		
		
	}
	
	// 수식 계산 
		static long cal(long n1, long n2, String o) {
			long res = 0;
			switch(o) {
			case "+" :
				res = n1 + n2;
				break;
			case "-" :
				res = n1 -n2;
				break;
			case "*" :
				res = n1 * n2;
				break;
			}
			
			return res;
		}
}
