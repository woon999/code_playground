package kakao.blind2021;


// blind #1 신규 아이디 추천

// 정규식, 문자열  (replaceAll 사용해서 다시 풀어보기) 
import java.util.*;
import java.util.regex.Pattern;

public class RecommendNewId {

	static List<String> spec = new ArrayList<>();
	public static void main(String[] args) {
//		String new_id = "...!@BaT#*..y.abcdefgh.jk lm";
		String new_id = "=.=";
		
		System.out.println(solution(new_id));
	}
	
    public static String solution(String new_id) {
        
        // 1단계 
        String[] a = new_id.toLowerCase().split("");
        
        List<String> list = new LinkedList<>();	
        
        for(String s : a) {
        	list.add(s);
        }
        
        spec = new ArrayList<>();
        spec.add("-");
        spec.add("_");
        spec.add(".");
        spec.add(" ");
        
        
        // 2단계 
        for(int i=0; i<list.size(); i++) {
        	String s = list.get(i);
        	if(!spec.contains(s) && !Pattern.matches("[a-z0-9]", s)) {
        		list.remove(i);
        		i--;
        	}
        }
        
        
        // 3단계 
        for(int i=1; i<list.size(); i++) {
        	String s = list.get(i);
        	if(s.equals(".") && s.equals(list.get(i-1))) {
        		list.remove(i);
        		i--;
        	}
        }
        
        
        // 4단계 
        dotCheck(list);
        
        // 5단계 
        if(list.size() ==0 || list.contains(" ")) {
        	for(int i=0; i<list.size(); i++) {
        		if(list.get(i).equals(" ")) {
        			list.set(i, "a");
        		}
        	}
        	
        	if(list.size()==0) list.add("a");
        }
        
        
//        for(int i=0; i<list.size(); i++) {
//          	String s = list.get(i);
//          	System.out.print(s);
//           }
//            System.out.println();
      
    
      // 6단계 
      while(list.size() > 15) {
    	  list.remove(15);
      }
      dotCheck(list);
      
      
//      for(int i=0; i<list.size(); i++) {
//      	String s = list.get(i);
//      	System.out.print(s);
//       }
        
      // 7단계 
      if(list.size() <3) {    	  
    	  String s = list.get(list.size()-1);
    	  while(true) {
    		  if(list.size() >= 3) break;
    		  list.add(s);
    	  }
      }
      
      StringBuilder sb = new StringBuilder();
      for(String s : list) {
    	  sb.append(s);
      }
      
        return sb.toString();
        
    }
    
    static void dotCheck(List<String> list) {
    	if(list.get(0).equals(".")) list.remove(0);
    	
    	if(list.size()>0 && list.get(list.size()-1).equals(".")) list.remove(list.size()-1);
    }
}
