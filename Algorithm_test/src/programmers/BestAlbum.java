package programmers;


// Hash #4 베스트앨범  
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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


class Solution5 {
	public int[] solution(String[] genres, int[] plays) {
        int len = genres.length;
        
        Map<String, Integer> streaming = new HashMap<>();
        List<String> rank = new ArrayList<>();
        List<Integer> trackNum = new ArrayList<>();
        
        
        // genre당 총 play수 저장 
        for(int i=0; i<len; i++) {
        	streaming.put(genres[i], streaming.getOrDefault(genres[i], 0)+ plays[i]);
        }
        
        
        // play수 높은대로 장르 정렬 
        for(String s : streaming.keySet()) {
        	System.out.println(s + ", " + streaming.get(s) );
        	rank.add(s);
        }
        
        rank.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return streaming.get(o2).compareTo(streaming.get(o1));
			}
		});
     
        
        // play수 높은 장르 순서대로 장르별 가장 play수 많은 두 곡 선정 후 trackNum저장 
        for(int i=0; i<rank.size(); i++) {
        	int hash = rank.get(i).hashCode();
        	System.out.println(rank.get(i));
        	int first =0, second =0;
        	
        	int max  = 0;
        	
        	for(int j=0; j<len; j++) {
        		if(hash == genres[j].hashCode()) {
        			if(plays[j] > max) {
        				max = plays[j];
        				first = j;
        			}
        		}
        	}
        	
        	max = -1;
        	for(int j=0; j<len; j++) {
        		if(hash == genres[j].hashCode()) {
        			if(j != first && plays[j] > max) {
        				max = plays[j];
        				second = j;
        			}
        		}
        	}
        	
        	System.out.println(first+","+second+"," +max);
        	trackNum.add(first);
        	if(max != -1) {
        		trackNum.add(second);
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
}