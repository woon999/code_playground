package kakao.blind2019;

//blind #1 오픈 채팅방 - 문자열, map
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpenChat {

	public static void main(String[] args) {
		String[] record = {"Enter uid1234 Muzi", 
				"Enter uid4567 Prodo",
				"Leave uid1234",
				"Enter uid1234 Prodo",
				"Change uid4567 Ryan"};
		
		System.out.println(Arrays.toString(solution(record)));
	}
	
	public static String[] solution(String[] record) {
        Map<String, String> userData = new HashMap<>(); 
        List<String[]> list = new ArrayList<>(); 
        
        for(int i=0; i<record.length; i++) {
        	String[] line = record[i].split(" ");
        	
        	String op = line[0];
        	String uid = line[1];
        	if(op.equals("Enter")) {
        		String name = line[2];
        		userData.put(uid, name);
        		list.add(new String[] {uid,"in"});
        		
        	}else if(op.equals("Leave")) {
        		String name = userData.get(uid);
        		list.add(new String[] {uid,"out"});
        		
        	}else if(op.equals("Change")) {
        		String name = line[2];
        		userData.put(uid, name);
        	}
        	
        }
        
        String[] answer = new String[list.size()];
        for(String[] s : list) { 
    		System.out.println(s[0] +", " + s[1]);
    	}
        
        for(int i=0; i<list.size(); i++) {
        	String[] result = list.get(i);
        	
        	if(result[1].equals("in")) {
        		answer[i] = userData.get(result[0])+"님이 들어왔습니다.";	
        	}else {
        		answer[i] = userData.get(result[0])+"님이 나갔습니다.";
        	}
        	
        }
        return answer;
    }
}
