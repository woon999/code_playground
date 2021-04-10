package kakao.blind2021;


// blind #2 메뉴 리뉴얼 
import java.util.*;
import java.util.Map.Entry;

public class MenuRenewal {
	
	static List<String> combi;
	public static void main(String[] args) {
		String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//		String[] orders = {"ABCFG", "AC"};
		int[] course = {2,3,4};
		
		System.out.println(solution(orders, course));
	}
	
	public static String[] solution(String[] orders, int[] course) {

		String[] answer = {};
                
        combi = new ArrayList<>();
        //코스 메뉴조합의 모든 경우의 수 구하기 (dfs) 
        for(int i=0; i<orders.length; i++) {
        	String[] one = orders[i].split("");
        	Arrays.sort(one);
        	
        	for(int j=0; j< one.length-1; j++) {
        		for(int k=0; k< course.length; k++) {
//        			System.out.println(course[k] + " 개의 코스구성 ");
        			dfs(one, j, 1, course[k], one[j]);
        		}
        	}
        }
        
        Map<String , Integer> map = new HashMap<>();
        for(String menu : combi) {
//        	System.out.println(menu);
        	map.put(menu, map.getOrDefault(menu, 0)+1);
        }
        
       
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return map.get(o2)-map.get(o1);
			}
		});

       
       List<String> res = new ArrayList<>();
       for(int i=0; i< course.length; i++) {
    	   int max =0;
    	   
    	   // course 갯수별로 가장 인기있는 품목 선정 
    	   for(String courseMenu : list) {
    		   if(map.get(courseMenu)>1 && courseMenu.length() == course[i]) {
    			   
    			   if(map.get(courseMenu) >= max) {
//    				   System.out.println(courseMenu +" : " + map.get(courseMenu));
    				   res.add(courseMenu);
    				   max = map.get(courseMenu);
    			   }
    		   }
    	   }
       }
       
       // 문자순 정렬 
       Collections.sort(res);
    	
       answer = new String[res.size()];
       res.toArray(answer);
        
        
        return answer;
    }
	
	static void dfs(String[] one, int idx, int len, int courseLen, String str) {
		if(len == courseLen) {
//			System.out.println("Coin! " + str);
			combi.add(str);
		}
		
		for(int i= idx+1; i<one.length; i++) {
			dfs(one, i, len+1, courseLen, str+one[i]);
		}
	}
	
	
}
