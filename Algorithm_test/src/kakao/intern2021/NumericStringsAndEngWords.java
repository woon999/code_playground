package kakao.intern2021;

// #1 kakao2021intern 숫자 문자열과 영단어 - 문자열
import java.util.HashMap;
import java.util.Map;
public class NumericStringsAndEngWords {

	public static void main(String[] args) {
		String s ="one4seveneight";
		String s1 ="23four5six7";
		
		System.out.println(solution(s1));
	}
	
	public static int solution(String s) {
        int answer = 0;
        Map<String, Integer> num_set = new HashMap<>();
        
        num_set.put("zero", 0);
        num_set.put("one", 1);
        num_set.put("two", 2);
        num_set.put("three", 3);
        num_set.put("four", 4);
        num_set.put("five", 5);
        num_set.put("six", 6);
        num_set.put("seven", 7);
        num_set.put("eight", 8);
        num_set.put("nine", 9);
        
        for(String num : num_set.keySet()) {
        	if(s.contains(num)) {
        		s = s.replace(num, ""+num_set.get(num));
        	}
        }
        answer = Integer.parseInt(s);
        return answer;
    }

}
