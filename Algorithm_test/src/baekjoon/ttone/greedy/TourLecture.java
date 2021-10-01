package baekjoon.ttone.greedy;

// #2109 그리디 순회강연 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class TourLecture {

	static class Schedule implements Comparable<Schedule>{
		int day;
		int pay;
		
		public Schedule(int day, int pay){
			this.day = day;
			this.pay = pay;
		}

		@Override
		public int compareTo(Schedule o) {
			if(o.pay!= this.pay) return o.pay - this.pay;
			else return this.day-o.day;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Schedule> list = new ArrayList<>();
		StringTokenizer st = null;
		int max = 0;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			max = Math.max(max, day);
			list.add(new Schedule(day, pay));
		}
		
		Queue<Schedule> q = new PriorityQueue<>();
		for(Schedule s : list) {
			q.add(s);	
		}
		
		boolean[] checked = new boolean[max+1];
		int result = 0;
		while(!q.isEmpty()) {
			Schedule s = q.poll();
			
			for(int i = s.day; i>0; i--) {
				if(!checked[i]) {
					checked[i] = true;
					result += s.pay;
					break;
				}
			}
		}
		System.out.println(result);
		
	}
}


