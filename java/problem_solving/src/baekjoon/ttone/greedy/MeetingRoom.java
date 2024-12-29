package baekjoon.ttone.greedy;

// #1931 그리디 회의실 배정 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class MeetingRoom {

	static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		
		public Meeting(int start, int end) {
			this.start =start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			if(this.end>o.end) return 1;
			else if(this.end<o.end) return -1;
			else return this.start-o.start;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Meeting> list = new ArrayList<>();
		StringTokenizer st = null;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.add(new Meeting(a,b));
		}
		
		Collections.sort(list);
		
		int endTime=0, cnt=0;
		for(Meeting m : list) {
			if(endTime<=m.start) {
				endTime = m.end;
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
}
