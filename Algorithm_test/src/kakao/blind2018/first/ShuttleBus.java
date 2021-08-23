package kakao.blind2018.first;

//blind #3 셔틀버스 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuttleBus {

	public static void main(String[] args){
		int n = 2;
		int t = 10;
		int m = 2;
		String[] timetable = {"09:10", "09:09", "08:00"};
		
		System.out.println(solution(n, t, m, timetable));
	}
	
	public static String solution(int n, int t, int m, String[] timetable) {
        StringBuilder sb = new StringBuilder();
        int arrivalTime = 540;
        
        List<Integer> list = new ArrayList<>();
        for(String data : timetable) {
        	String[] time = data.split(":");
        	int hh = Integer.parseInt(time[0]);
        	int mm = Integer.parseInt(time[1]);
        	list.add(hh*60+mm);
        }
        
        Collections.sort(list);
        int seat =0;
        while(n>0) {
        	seat =m;
        	
        	if(n==1) {
        		int tt =0;
        		int hh =0;
        		int mm =0;
        		if(list.size()>=m) {
        			if(list.get(m-1) <= arrivalTime) {
        				tt = list.get(m-1)-1;	
        			}else {
        				tt =arrivalTime;
        			}
        			System.out.println("1 도착 : " + tt);
        		}else {
        			System.out.println("2 도착 : " + arrivalTime);
        			tt = arrivalTime;
        			
        		}
        		if(tt/60>0) {
    				hh = tt/60;
    				mm = tt%60;
    			}else {
    				mm = tt;
    			}
        		
        		if(hh<10) {
        			sb.append("0");
        		}
        		sb.append(""+hh+":");
        		if(mm<10) {
        			sb.append("0");
        		}
        		sb.append(""+mm);
        	}
        	else {
	        	while(seat>0) {
	        		if(list.get(0)<=arrivalTime) {
	        			list.remove(0);
	        			seat--;
	        		}else break;
	        	}
        	}
        	n--;
        	arrivalTime+=t;
        }      
        return sb.toString();
    }
}
