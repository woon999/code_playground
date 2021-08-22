package kakao.blind2018.first;

//blind #5 캐시 - 자료구조, 큐 
import java.util.LinkedList;
import java.util.Queue;

public class Cache {

	public static void main(String[] args) {
		
//		int cacheSize = 3;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		
//		int cacheSize = 0;
//		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		
		int cacheSize = 5;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
		
		System.out.println(solution(cacheSize, cities));
	}
	
	public static int solution(int cacheSize, String[] cities) {
        Queue<String> q = new LinkedList<>();
        int time =0;
        for(int i=0; i<cities.length; i++) {
        	String city = cities[i].toLowerCase();
        	
        	if(!q.contains(city)) {
        		time +=5;
        	}else {
        		q.remove(city);
        		time +=1;
        	}
        	q.add(city);
        	
        	if(q.size()>cacheSize) {
        		q.poll();
    		}
        }
        return time;
    }
}
