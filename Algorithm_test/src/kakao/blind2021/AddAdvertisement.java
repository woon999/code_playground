package kakao.blind2021;

// blind #5 광고 삽입 (스케쥴링, 우선순위 큐) - 다시풀기 
import java.util.*;

// 1. String data를 int로 다룰 수 있는지 생각하기 

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
        String answer = "";
        
//        String[] play_times = play_time.split(":");
//        String[] adv_times = adv_time.split(":");
        
        int ad_time = Integer.parseInt(adv_time.replaceAll(":", ""));
        
        
        Queue<UserData> data = new PriorityQueue<>();
        for(int i=0; i<logs.length; i++) {
        	String[] log = logs[i].split("-");
//        	System.out.println(log[0]+"--"+log[1]);
        	int a = Integer.parseInt(log[0].replaceAll(":", ""));
        	int b = Integer.parseInt(log[1].replaceAll(":", ""));
        	data.add(new UserData(i+1, a, b));
        	
        }
        System.out.println();
   
        
        UserData pos = data.poll();
    	int start = pos.start;
    	int end = pos.end;
//    	System.out.println("시간 : " +start + " ~ " + end);
    	
    	int max = -1;
    	int adStartTime = 0;
    	int adCount=1; // 광고 시청 인원 
    	int exposureTime =0; //광고 노출시간 
        while(!data.isEmpty()) {
        	UserData next = data.poll();
        	int n_start = next.start;
        	int n_end = next.end;
        	
//        	System.out.println("시간 : " +n_start + " ~ " + n_end);
        	
        	if(end >= n_start) {
        		
        		if(adCount ==1) {
        			start =n_start;
        			exposureTime += (end-start);
//        			System.out.println("play : " +start + " ~ " + end +"=" + (end-start));
        		}
        		adCount ++;
        		exposureTime += (end-n_start);
//        		System.out.println("play : " +  n_start +" ~ " + end +"=" + (end-n_start));
//        		System.out.println("ad start!" + n_start + " -> 시청인원:" + adCount +", 누적시간 : " + exposureTime);
//        		if((end-n_start) >= ad_time){
//        			
//        			System.out.println((end -n_start)+" >="+ adv_time);
//        			System.out.println("coin!");
//        		}

        	}
        	else {
        		
        		start =n_start;
        		end = n_end;
//        		System.out.println("총 시청인원 : " + adCount);
//        		System.out.println("광고 노출시간 :" + exposureTime );
//        		System.out.println("reset! new :" + start+ "~"+end);
        		adCount =1;
        		exposureTime=0;
//        		System.out.println();
        	}
        	
        	if(exposureTime > max) {
        		max = exposureTime;
        		adStartTime = start;
        	}
        }
        
//       System.out.println("최고 시청인원 :" + max);
//       System.out.println("광고 노출시간 :" + exposureTime );
//       System.out.println("광고 시작시간 :" + adStartTime );
       
       answer += adStartTime;
       while(answer.length() < 6) {
    	   answer = "0" + answer;
       }
       
       
       String hour = answer.substring(0, 2);
       String minutes = answer.substring(2, 4);
       String seconds = answer.substring(4, 6);
       
        return hour+":"+minutes+":"+seconds;
    }
	

}

class UserData implements Comparable<UserData>{
	int userNum;
	int start;
	int end;
	
	public UserData(int userNum, int start, int end) {
		this.userNum = userNum;
		this.start = start;
		this.end = end;
	}

	// start기준으로 오름차순
	@Override
	public int compareTo(UserData o) {
		// TODO Auto-generated method stub
		return this.start- o.start;
	}
}
