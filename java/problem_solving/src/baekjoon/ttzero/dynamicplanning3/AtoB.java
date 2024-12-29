package baekjoon.ttzero.dynamicplanning3;

// #16953
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class AtoB {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		solve(a, b);
		
	}
	
	static void solve(long a, long target) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(a, 0));
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			if(p.x == target) {
				System.out.println(p.cnt + 1);
				return;
			}
			
			for(int i=0; i<2; i++) {
				long x = 0;
				
				if(i==0) {
					x = 2*p.x;
				} else {
					x = 10*p.x +1;
				}
				
				if(x <= target) {
					q.add(new Point(x, p.cnt+1));
				}
			}
		}
		System.out.println(-1);
	}
	
}

class Point{
	long x;
	int cnt;
	
	public Point(long x, int cnt) {
		this.x =x;
		this.cnt =cnt;
	}
}
