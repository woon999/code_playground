package kakao.blind2021;


// blind #2 메뉴 리뉴얼 
import java.util.*;
import java.util.Map.Entry;

public class MenuRenewal {

	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int[] course = {2,3,4};
		
		System.out.println(solution(orders, course));
	}
	
	public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        Map<String , Integer> map = new HashMap<>();
        
        for(String s : orders) {
        	String[] arr = s.split("");
        	for(int i=0; i<arr.length; i++) {
//        		System.out.println(arr[i]);
        		map.put(arr[i], map.getOrDefault(arr[i], 0)+1);
        	}
        }
        
        List<Entry<String, Integer>> list= new ArrayList<Entry<String, Integer>>(map.entrySet());

       Collections.sort(list, new Comparator<Entry<String, Integer>>() {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			// TODO Auto-generated method stub
			return o2.getValue().compareTo(o1.getValue());
		}
	});

        for(Entry<String, Integer> s : list) {
        	System.out.println(s.getKey() + " : " + s.getValue());
        }
        
        
        return answer;
    }
}
