package programmers;


// Hash #1
import java.util.HashMap;
import java.util.Map;

public class Marathon {

	public static void main(String[] args) {
		
		Solution2 s = new Solution2();
		
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"mislav", "stanko", "ana"};
		
		System.out.println(s.solution(participant, completion));
		
		
	}
	
	
}


class Solution2{
	public String solution(String[] participant, String[] completion) {
		String answer = "";
		
		// boolean은 중복을 표현할 방법이 없음 
		Map<String, Integer> player = new HashMap<>();
		
		for(String s : participant) {
			if(player.get(s) != null) {
				player.put(s, player.get(s) +1);
			}else {
				player.put(s, 1);
			}
		}
		
		for(String s : completion) {
			player.put(s, player.get(s) -1);
		}
		
		for(String key : player.keySet()) {
//			System.out.println("get :" + player.get(key) +", key : " +key);
			if(player.get(key) ==1) {
				answer=key;
			}
		
		}
		
		
		
		return answer;
	}

}