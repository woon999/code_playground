package programmers;


// Hash #4 베스트앨범  
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class BestAlbum {

	public static void main(String[] args) {
		Solution5 s = new Solution5();
		
		String[] genres = {"jazz", "pop", "classic", "classic", "pop"};
		
		int[] plays = {150, 600, 500, 800, 2500};
		
		s.solution(genres, plays);
	}
}


@SuppressWarnings("unchecked")
class Solution5 {
	public int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        
        Map<String, Object> tracks = new HashMap<>();
        Map<String, Integer> streaming = new HashMap<>();
        List<String> rank = new ArrayList<>();
        List<Integer> trackNum = new ArrayList<>();
        
        
        // tracks : 장르별 (트랙 넘버, 플레이수)로 저장
        // streaming : 장르별 총 플레이수 저장 
        for(int i=0; i<len; i++) {
        	Map<Integer, Integer> map;  
        	
        	if(tracks.containsKey(genres[i])) {
        		map = (HashMap<Integer, Integer>) tracks.get(genres[i]); // ex. {1=600}
        	}else {
        		map = new HashMap<>();
        	}
        	
        	map.put(i, plays[i]);
        	tracks.put(genres[i], map); // ex. key: pop, value: {1=600, 4=2500}
        	
        	
        	// 장르별 총 스트리밍 수 
        	streaming.put(genres[i], streaming.getOrDefault(genres[i], 0)+ plays[i]);
        }
        
        
        // play수 높은대로 장르 정렬 
        rank = sortToList(streaming);
     
        
        int count =0;
        // play수 높은 장르 순서대로 장르별 가장 play수 많은 두 곡 선정 후 trackNum저장 
        for(int i=0; i<rank.size(); i++) {
        	String key = rank.get(i);
        	int max  = 0;
        	
        	System.out.println(rank.get(i));
        	System.out.println(tracks.get(key));
        	
        	Iterator it = sortToList((HashMap<Integer,Integer>) tracks.get(key)).iterator();
        	
        	while(it.hasNext()) {
        		int num = (int)it.next();
        		System.out.println("sd: "+ num);
        		trackNum.add(num);
        		count++;
        		max++;
        		if(max >=2 ) break;
        		
        	}
        
        }
        
        
        // bestAlbum에 담을 trackNum int[] 배열에 다시 저장 
        int[] answer = new int[trackNum.size()];
        for(int i=0; i<trackNum.size(); i++) {
        	answer[i] = trackNum.get(i);
        	System.out.println(i + ": "+ trackNum.get(i));
        }
        
        
        return answer;
    }
	
	@SuppressWarnings({ "unused", "rawtypes" })
	private ArrayList sortToList(Map map){
        ArrayList<Object> list = new ArrayList();
        list.addAll(map.keySet());

        list.sort(new Comparator<Object>(){
            public int compare(Object o1, Object o2){

                return ((Comparable)map.get(o2)).compareTo(map.get(o1));
            }
        });

        return list;
    }
}