package kakao.blind2018.first;

// blind #1 추석 트래픽 - 중간 저장 

// 슬라이딩 범위 더 세분화 시켜야할듯 

public class Traffic {

	public static void main(String[] args) {
//		String[] lines = {"2016-09-15 00:00:00.000 0.3s"};
//		String[] lines = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
		String[] lines = {"2016-09-15 20:59:57.421 0.351s",
		"2016-09-15 20:59:58.233 1.181s",
		"2016-09-15 20:59:58.299 0.8s",
		"2016-09-15 20:59:58.688 1.041s",
		"2016-09-15 20:59:59.591 1.412s",
		"2016-09-15 21:00:00.464 1.466s",
		"2016-09-15 21:00:00.741 1.581s",
		"2016-09-15 21:00:00.748 2.31s",
		"2016-09-15 21:00:00.966 0.381s",
		"2016-09-15 21:00:02.066 2.62s"};
		
		solution(lines);
	}
	
	public static int solution(String[] lines) {
        int answer = 0;
        int len = lines.length;
        String s = lines[0].substring(11, lines[0].length()-1);
        int st = initTime(s, false);
        
        String e = lines[len-1].substring(11, lines[len-1].length()-1);
        int et = initTime(e, true);
        
        System.out.println(st+"~ " +et);
        
        int size = et-st+1;
        int[] dp = new int[size];
        
        int comp = st;
        
        int max = 1;
        System.out.println("------");
        for(int i=0; i<len; i++) {
        	lines[i] = lines[i].substring(11, lines[i].length()-1);
        	
        	int[] time = timeToSec(lines[i], comp);
        	System.out.println(time[0] +" ~ "+ time[1]);
        	
        	for(int j=time[0]; j<=time[1]; j++) {
        		dp[j]++;
        		if(max < dp[j]) {
        			max = dp[j];
        		}
        	}
        }
        System.out.println(max);
        
        return answer;
	}
	
	static int[] timeToSec(String time, int comp) {
		String[] data = time.split(" ");
        int tp = removeDot(data[1]);
		
		String[] t = data[0].split(":");
		int hh = Integer.parseInt(t[0]);
		int mm = Integer.parseInt(t[1]);
		int ss = removeDot(t[2]);
		
		int sSec = ss-tp+1;
		int start = hh*3600 + mm*60 + (sSec/1000)-comp;
//		if(sSec%1000>0) start++;
		
		int end  = hh*3600 + mm*60 + (ss/1000)-comp;
		
		return new int[] {start, end};
	}
	
	static int initTime(String time,  boolean flag) {
		String[] data = time.split(" ");
        int tp = removeDot(data[1]);
		
		String[] t = data[0].split(":");
		int hh = Integer.parseInt(t[0]);
		int mm = Integer.parseInt(t[1]);
		int ss = removeDot(t[2]);
		
		if(!flag) {
			ss = ss-tp+1;
		}
		
//		System.out.println(hh+"," +mm+","+ss +" .." + tp);
		
		int total = hh*3600 + mm*60 + (ss/1000);
		if(flag && ss%1000 >0 ) total++;
		
		return total;
		
	}
	
	static int removeDot(String sec) {
        int ttp =0;
        if(sec.contains(".")) {
	        String[] t = sec.split("\\.");
	        int a = Integer.parseInt(t[0]);
	        int b = Integer.parseInt(t[1]);
	        int bLen = t[1].length();
	        
	        ttp = (int) (a*1000+b*Math.pow(10, 3-bLen));
        }else {
        	ttp = Integer.parseInt(sec)*1000;
        }
        
        return ttp;
		
	}
	
}
