package programmers.ttone;

// 그리디 #2 조이스틱 
public class JoyStick {

	public static void main(String[] args) {
//		String name = "JAN";
		String name = "BBABBB";
		
		System.out.println(solution(name));
	}
	
    public static int solution(String name) {
        int answer = 0;
        int min = name.length()-1;
        
        for(int i=0; i<name.length(); i++) {
        	char spell = name.charAt(i);
        	int fromA = spell - 'A'; 
        	int fromZ = 'Z' -spell +1;
        	
        	answer += fromZ > fromA ? fromA : fromZ;
//        	System.out.println(fromA + " , " + fromZ + " : " + answer);
        	
        	int next =i+1;
        	while( next < name.length()
        			&& name.charAt(next) == 'A') {
       			next++;
       		}
        	System.out.println("next : " + next + " , i : " + i);
        	
        	// i : 현재까지 이동횟수
        	// name.length() - next : 총 길이에서 연속된 A가 지나고 남은 문자 수 
        	// i + name.length() - next : i문자 옆에오는 A개수를 합한 인덱스 값 

        	// Math.min(i, name.length()-next) : 왼쪽에서 접근, 오른쪽에서 접근 중 작은 경우를 고름 
    		min = Math.min(min,
    				i+name.length() - next + Math.min(i, name.length()-next));
    		// ex. BBAAABBB 에서 i =1일 때,  next =5
        	// BB 왼쪽으로 돌아가는 것이 더 작은므로 i = 1을 택해서 min = 5 // (len -next) : 3
    		
        	
    		System.out.println("min : " +min);
        	
        }
        
        return answer+min;
    }
}
