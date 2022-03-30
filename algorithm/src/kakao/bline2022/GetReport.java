package kakao.bline2022;

// #1 kakao2022blind 신고 결과 받기 

import java.util.*;
public class GetReport {
	
	public static void main(String[] args) {
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k =2;
		
//		String[] id_list = {"con", "ryan"};
//		String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
//		int k =3;
		
		System.out.println(Arrays.toString(solution(id_list, report, k)));
		
	}

	public static int[] solution(String[] id_list, String[] report, int k) {
		Map<String, Set<String>> reportMap = new HashMap<>();
		
		for(String rep : report) {
			String[] r = rep.split(" ");
			
			if(!reportMap.containsKey(r[1])) {
				Set<String> set = new HashSet<>();
				set.add(r[0]);
				reportMap.put(r[1], set);
			}else {
				reportMap.get(r[1]).add(r[0]);	
			}
			
		}
		
		Map<String, Integer> idMap = new LinkedHashMap<>();
		for(String id : id_list) {
			idMap.put(id, 0);
		}
		
		for(String key : reportMap.keySet()) {
			Set<String> list = reportMap.get(key);
			if(list.size() >= k) {
				for(String repId : list) {
					idMap.put(repId, idMap.getOrDefault(repId, 0)+1);
				}
			}
		}
		
		int[] answer = new int[id_list.length];
		int idx = 0;
		for(String key : idMap.keySet()) {
			answer[idx++] = idMap.get(key);
		}
	    
	    return answer;
	}
}
