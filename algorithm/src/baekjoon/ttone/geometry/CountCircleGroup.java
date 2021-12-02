package baekjoon.ttone.geometry;

// #10216 geometry Count Circle Group - 유니온파인드 
import java.io.*;
import java.util.*;

public class CountCircleGroup {
	
	static class Circle{
		int x;
		int y;
		int r;
		
		public Circle(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		
	}
	
	static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		while(tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			Circle[] circles = new Circle[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				circles[i] = new Circle(x, y, r);
			}
			
			parents = new int[n];
			for(int i=0; i<n; i++) {
				parents[i] = i;
			}
			
			for(int i=0; i<n; i++) {
				for(int j=i+1; j<n; j++) {
					Circle c1 = circles[i];
					Circle c2 = circles[j];
					
					if(find(i) == find(j)) continue;
					int r = (int) Math.pow(c1.r+c2.r, 2);
					int dis =  (int) (Math.pow(c1.x-c2.x, 2)+Math.pow(c1.y-c2.y, 2));
					if(r >= dis) {
						union(i, j);
					}
				}
			}
			
			Set<Integer> set = new HashSet<>();
			for(int num : parents) {
				System.out.println(num);
				set.add(find(num));
			}
			System.out.println(set.size());
		}
	}
	
	static int find(int x) {
		if(x == parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		parents[y] = x;
	}
}


