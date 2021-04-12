package kakao.blind2021;


// blind #3 순위 검색  (효율성0점...) 
import java.util.*;


public class RankSearch {
	
	static Map<String, ArrayList<Integer>> allInfo;
	static ArrayList<Integer> in;
	public static void main(String[] args) {
		String[] info = {
				"java backend junior pizza 150",
				"python frontend senior chicken 210",
				"python frontend senior chicken 150",
				"cpp backend senior pizza 260",
				"java backend junior chicken 80",
				"python backend senior chicken 50"
		};
		
		String[] query = {
				"java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200",
				"cpp and - and senior and pizza 250",
				"- and backend and senior and - 150",
				"- and - and - and chicken 100",
				"- and - and - and - 150"
		};
		
		System.out.println(Arrays.toString(solution(info, query)));
	}
	
	public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        allInfo = new HashMap<>();
        
        // 1. info 모든 경우의 수 map에 저장 
        for(int i=0; i<info.length; i++) {
//        	System.out.println(Arrays.toString(info[i].split(" ")));
        	dfs("",0, info[i].split(" "));
        }
        
        // 2. map에 저장된 점수 list 오름차순으로 정렬 	
        List<String> list = new ArrayList<>(allInfo.keySet());
        for(int i=0; i<list.size(); i++) {
        	List<Integer> scoreList = allInfo.get(list.get(i));
        	Collections.sort(scoreList);
        }
        
        
    	// 3. 이분 탐색
        for(int i=0; i<query.length; i++) {
        	query[i] = query[i].replaceAll(" and ", "");
        	String[] str = query[i].split(" ");
        	int score = Integer.parseInt(str[1]);
        	
//        	System.out.println("idx : " +search(str[0], score));
        	answer[i] = search(str[0], score);
        }
      
        return answer;
    }
	
	static void dfs(String pos, int depth, String[] info) {
		
		if(depth == 4) {
//			System.out.println(pos);
			if(!allInfo.containsKey(pos)) {
				in = new ArrayList<>();
				in.add(Integer.parseInt(info[4]));
				allInfo.put(pos, in);
			}else {
				allInfo.get(pos).add(Integer.parseInt(info[4]));
			}
			
			return;
		}
		
		dfs(pos+"-", depth+1, info);
		dfs(pos+info[depth], depth+1, info);
		
	}
	
	
	// 이분 탐색 
	static int search(String str, int score) {
		if(!allInfo.containsKey(str)) return 0;
		
		List<Integer> scoreList = allInfo.get(str); 
    	int start= 0, end = scoreList.size()-1;
    	while(start<=end) {
    
    		int mid =(start+end)/2;
    		if(scoreList.get(mid) <score) {
    			start = mid+1;	
    		}else {
    			end = mid-1;
    		}
    		
    	}
    	System.out.println(scoreList.size() + " - " +start);
    		
    	return scoreList.size()-start;
	}
}
