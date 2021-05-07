package kakao.intern2020;

//intern #3 보석 쇼핑 (Set, Map)  
import java.util.*;

public class JewelShopping {

	static int[] gem_list;
//	static int start=1, end=0;
	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		
		System.out.println(solution(gems));
		
	}
	
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        Map<String, Integer> jewel_list = new HashMap<>(); 
        int idx=1;
        for(String gem : gems) {
        	if(!jewel_list.containsKey(gem)) {
        		jewel_list.put(gem, idx++);
        	}
        }
        
        gem_list = new int[gems.length];
        for(int i=0; i<gems.length; i++) {
        	int gem_num = jewel_list.get(gems[i]);
        	gem_list[i] = gem_num;
        }
        
        for(int num : gem_list) {
        	System.out.print(num+" ");
        }
        System.out.println();
        
        int total_jewel = jewel_list.size(); 
        
//        solve(total_jewel);
        
        
        int start = 0;
        int tempStart = 0;
        int len = gems.length;
    	Map<Integer, Integer> bag_map = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < gem_list.length; i++) {
        	bag_map.put(gem_list[i], bag_map.getOrDefault(gem_list[i], 0) + 1);
            
            q.add(gem_list[i]);
            while(true) {
                int gem = q.peek();
                System.out.println(i +" ,"+gem);
                if(bag_map.get(gem) > 1) {
                	bag_map.put(gem, bag_map.get(gem) - 1);
                	q.poll();
                    tempStart++;
                    System.out.println(i+"-> " +tempStart);
                } else break;
            }
            
            if(bag_map.size() == total_jewel) {
                if(len > q.size()) {
                    len = q.size();
                    start = tempStart;
                }
            }
        }

        System.out.println(start+1 +" ~ " + (start+len));
        
        
        answer[0] = start+1;
        answer[1] = start+len;
        return answer;
    }
    
//    static void solve(int total) {
//    	
//    	Map<Integer, Integer> bag_map = new HashMap<>();
//    	
//    	for(int i=0; i<gem_list.length; i++) {
//    		bag_map.put(gem_list[i], bag_map.getOrDefault(gem_list[i], 0)+1);
//    		
////    		bag.offer(gem_list[i]);
//    		
//    		if(bag_map.size()==total) {
//    			System.out.println(i+" 여기까지 ");
//    			end =i+1; 
//    			break;
//    		}
//    	
//    	}
//    	
////    	for(int num : bag_map.keySet()) {
////    		System.out.println(num+" ," + bag_map.get(num));
////    	}
//    	bag_map.clear();
//    	for(int i=end-2; i>0; i--) {
//    		bag_map.put(gem_list[i], bag_map.getOrDefault(gem_list[i], 0)+1);
//    		
////    		bag.offer(gem_list[i]);
//    		
//    		if(bag_map.size()==total-1) {
//    			System.out.println(i+" 여기까지 ");
//    			start =i+1; 
//    			break;
//    		}
//    	}
//    	
//    	System.out.println(start+","+end);
//    }
}

