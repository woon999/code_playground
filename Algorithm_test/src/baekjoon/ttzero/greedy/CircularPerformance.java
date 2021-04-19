package baekjoon.ttzero.greedy;

// #2109
import java.io.*;
import java.util.*;

public class CircularPerformance {

	static int n;
	static boolean[] checked;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());

		PriorityQueue<Perform> pq = new PriorityQueue<Perform>();
		
		int max = 0;
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int pay = Integer.parseInt(st.nextToken());
			int day = Integer.parseInt(st.nextToken());
			max = Math.max(max, day);
			pq.add(new Perform(pay, day));
		}
		
		checked = new boolean[max+1];
		int result=0;
		
		while(!pq.isEmpty()) {
			Perform p = pq.poll();
			for(int i = p.day; i>0; i--) {
				if(!checked[i]) {
					checked[i] = true;
					result += p.pay;
					break;
				}
			}
		}
		
		System.out.println(result);
		
		
	}
}

class Perform implements Comparable<Perform>{
	int pay;
	int day;
	
	public Perform(int pay, int day) {
		this.pay = pay;
		this.day = day;
	}

	@Override
	public int compareTo(Perform o) {
		if(o.pay > this.pay) {
			return 1;
		}else if(o.pay == this.pay) {
			if(o.day < this.day) {
				return 1;
			}
		}
		return -1;
	}
}
