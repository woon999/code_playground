package baekjoon.sort;

// #10165
// vector 공부 
import java.io.*;
import java.util.*;


public class BusLine {

	
	public static void main(String[] args) throws Exception{ 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
				
		Vector<Course> v = new Vector<>();
		int back =0;
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			
				v.add(new Course(i+1, start, end));
			if(v.get(i).s > v.get(i).e) {
				back = Math.max(back, v.get(i).e);
				v.get(i).e += n;
			}
		}
		
		Collections.sort(v);
		
		
		Deque<Course> q = new ArrayDeque<>();
		
		for (Course b : v) {
			if (q.isEmpty() || q.getLast().e < b.e) {
				q.addLast(b);
			}
		}

		while (!q.isEmpty() && q.getFirst().e <= back) {
			q.removeFirst();
		}

		Vector<Course> res = new Vector<>();
		while (!q.isEmpty()) {
			res.add(q.pop());
		}

		Collections.sort(res, new Comparator<Course>() {

			@Override
			public int compare(Course o1, Course o2) {
				return o1.idx - o2.idx;
			}

		});

		for (Course b : res) {
			System.out.print(b.idx+" ");
		}
	}

}

class Course implements Comparable<Course> {

	int idx;
	int s;
	int e;

	public Course(int idx, int s, int e) {
		this.idx = idx;
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(Course o) {
		if(this.s == o.s) {
			return o.e - this.e;
		}
		return this.s - o.s;
	}
}