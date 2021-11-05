package baekjoon.ttone.greedy;

// #10165 greedy 버스 노선 - sort 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BusRoute {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  null;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		int last =0;
		List<Route> list = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			if(s>e) {
				last = Math.max(last, e);
				e += n;
			}
			list.add(new Route(i+1, s,e));
		}
		
		Deque<Route> q= new ArrayDeque<>();
		Collections.sort(list);
		for(Route r : list){
			if(q.isEmpty() || q.getLast().e < r.e ) {
//				System.out.println(r.idx +" : " + r.s +"," + r.e);
				q.add(r);
			}
		}
		
		System.out.println(last);
		while (!q.isEmpty() && q.getFirst().e <= last) {
			Route r = q.removeFirst();
//			System.out.println(r.idx +" : " + r.s +"," + r.e);
		}
		
		List<Integer> res = new ArrayList<>();
		while (!q.isEmpty()) {
			int idx = q.poll().idx;
			res.add(idx);
		}
		Collections.sort(res, (a,b) -> (a-b));
		for(int idx : res) {
			System.out.print(idx+" ");
		}
	}
}

class Route implements Comparable<Route>{
	int idx;
	int s;
	int e;
	
	public Route(int idx, int s, int e) {
		this.idx = idx;
		this.s = s;
		this.e = e;
	}

	@Override
	public int compareTo(Route o) {
		if(this.s == o.s) {
			return o.e - this.e; // 시작이 같을 경우,끝 노선이 
		}
		return this.s - o.s; // 시작 노선 오름차순 
	}
}
