package kakao.blind2019;

// blind #4 무지 먹방 라이브  - 자료구조 (풀이참고)

// 효율성 테스트까지 고려해서 생각하면 설계가 몹시 어려웠다. 
// k를 최대한 줄이는 방향으로 가는 것 까지 정렬해서 설계하고 
// 남은 k로 남은 음식들을 카운트해주는 것 까지도 어거지로 구현했지만 
// 아이디어는 맞앗지만 쓸데없는 연산으로 인해 시간초과 발생

// 결국 풀이참고 마지막으로 한 번 더 풀어보기 

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EatLive {

	static List<Integer> foodList;
	public static void main(String[] args) {
		int[] food_times = {3,1,2};
		long k = 5;
	
//		int[] food_times = {70,20,10};
//		long k = 51;
	
		System.out.println(solution(food_times, k));
	}
	
	public static int solution(int[] food_times, long k) {

		int answer = 0;
        int size = food_times.length;   
        foodList = new ArrayList<>();
        for(int i=0; i<size; i++) {
        	foodList.add(i);
        }
        
        Collections.sort(foodList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return food_times[o1]-food_times[o2];
			}
		});
        
        int prev = 0;
        int idx=0;
        for(int fIdx : foodList) {
        	long sub = food_times[fIdx] - prev;
        	if(sub>0) {
        		long tmp = sub*size;
        		if(tmp <= k) {
        			k -= tmp;
        			prev = food_times[fIdx];
        		}else {
        			k %= size;
        			Collections.sort(foodList.subList(idx, food_times.length));
//        			System.out.println("#" + foodList.get(idx+(int)k));
        			return foodList.get(idx+(int)k)+1;
        		}
        	}
        	idx++;
        	size--;
        }
        return -1;
    }
	
}
