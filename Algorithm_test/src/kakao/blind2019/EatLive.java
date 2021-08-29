package kakao.blind2019;


public class EatLive {

	public static void main(String[] args) {
		int[] food_times = {3,1,2};
		long k = 5;
	
		System.out.println(solution(food_times, k));
	}
	
	public static int solution(int[] food_times, long k) {
        int answer = 0;
        
        int size = food_times.length;
        int idx=0;
        while(k>0) {
        	if(food_times[idx] ==0) {
        		System.out.println(idx+" finish");
        		idx++;
        		if(idx==size) idx=0;
        	}
        	
        	food_times[idx]--;
        	System.out.println(idx+" eat");
        	idx++;
        	if(idx==size) idx=0;
        	k--;
        }
        return idx+1;
    }
}
