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
		Map<String, Integer> index = new HashMap<>();
	    Map<String, String> userList = new HashMap<>();
	    Map<String, Integer> reportMap = new HashMap<>();
	    
	    for(int i=0; i<id_list.length; i++) {
	    	userList.put(id_list[i], "");
	    	reportMap.put(id_list[i], 0);
	    	index.put(id_list[i], i);
	    }
	    
	    
	    Set<String> reportSet = new HashSet<>();
	    for(String r : report) {
	    	reportSet.add(r);
	    }
	    
	    for(String r : reportSet) {
	    	String[] reportData = r.split(" ");
	    	userList.put(reportData[0], userList.get(reportData[0])+reportData[1]+" ");
	    	reportMap.put(reportData[1], reportMap.get(reportData[1])+1);
	    }
	    
	    
	    List<String> reportList = new ArrayList<>(reportMap.keySet());
	    Collections.sort(reportList, (a,b) -> reportMap.get(b) - reportMap.get(a));
	    
	    int idx=0;
	    for(int i=0; i<reportList.size(); i++) {
	    	String key = reportList.get(i);
	    	if(reportMap.get(key) <k) {
	    		idx = i;
	    		break;
	    	}
	    }
	    
	    reportList = reportList.subList(0, idx);
	    int[] answer = new int[id_list.length];
	    // 비교 
	    for(String key : userList.keySet()) {
	    	int cnt = 0;
	    	String[] list = userList.get(key).split(" ");
	    	for(int i=0; i<list.length; i++) {
	    		if(reportList.contains(list[i])) {
	    			cnt++;
	    		}
	    	}
	    	answer[index.get(key)] = cnt;	
	    }
	    return answer;
	}
}
