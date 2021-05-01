package kakao.blind2021;

// blind #5 광고 삽입  (최대 구간합) - 다시풀기 

// 1. String data를 int로 다룰 수 있는지 생각하기
// 2. 공간 크기 제한, 이중 포문 시간 복잡도 고려 

public class AddAdvertisement {

	public static void main(String[] args) {
		String play_time= "02:03:55";
		String adv_time="00:14:15";
		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00",
						"00:25:50-00:48:29", "01:30:59-01:53:29", 
						"01:37:44-02:02:30"};
		
		System.out.println(solution(play_time, adv_time, logs));
	}
	
	public static String solution(String play_time, String adv_time, String[] logs) {
		int play_len = timeToInt(play_time);
		int adv_len = timeToInt(adv_time);
		System.out.println(play_len);
		System.out.println(adv_len);
		
		int[] ad = new int[360_000];
		
		for(String log : logs) {
			String[] l = log.split("-");
			int start = timeToInt(l[0]);
			int end =  timeToInt(l[1]);
//			System.out.println(timeToInt(l[0]) + " ~ " + timeToInt(l[1]));
			for(int i=start; i<end; i++) {
				ad[i]++;
			}
		}
		
		// 최대 시청 인원 구간 찾기 
		// 최대 구간합 ( adv_time )
		// 구간합을 이중 포문이 아닌 queue와 같이 구간 맨 앞의 값을 빼주고 새로운 값 추가하는 식으로 효율성증가   
		int max_idx=0;
		long max_sum=0;
		long sum =0;
		for(int i=0; i<adv_len; i++) {
			sum += ad[i];
		}
		max_sum = sum;
		
		for(int i = adv_len; i<play_len; i++) {
			sum += ad[i] - ad[i-adv_len];
			if(sum > max_sum) {
				max_sum = sum;
				max_idx = i-adv_len+1;
			}
		}
		
		System.out.println(max_sum +" : " +timeToString(max_idx));
        
		return timeToString(max_idx);
    }
	
	static int timeToInt(String time) {
		String[] times = time.split(":");
		int toSec = 3600;
		int totalTime = 0;
		for(String t : times) {
			int num = Integer.parseInt(t);
			totalTime += num*toSec;
			toSec /= 60;
		}
		return totalTime;
	}
	
	static String timeToString(int time) {
		String t = "";
		int hour = time/3600;
		time %= 3600;
		if(hour <10) t+= "0"+ hour +":";
		else t += hour+":";
		
		int minute = time/60;
		time %= 60;
		if(minute <10) t+= "0"+ minute+":";
		else t += minute+":";
		
		int second = time;
		if(second <10) t+= "0"+ second;
		else t += second;
		
		return t;
	}
	

}

