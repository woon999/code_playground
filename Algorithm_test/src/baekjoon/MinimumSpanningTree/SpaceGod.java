package baekjoon.MinimumSpanningTree;

// #1774 
import java.io.*;
import java.util.*;

public class SpaceGod {

	public static int[] parent;
	public static ArrayList<God> list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		Point2[] points = new Point2[n];
		
		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
		
			points[i] = new Point2(x,y,i);
			
		}
		
		
		list = new ArrayList<God>();
		
		for(int i=0; i<n; i++) {
			for(int j=i+1; j<n; j++) {
		
				double weight = dis(points[i], points[j]);

				list.add(new God(points[i].num+1, points[j].num+1, weight));
			}
		}
		
		Collections.sort(list);
		
		parent = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			parent[i] = i;
		}

		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			int x = find(from);
			int y=  find(to);
			if(x!=y) union(from,to);
		}
		
		
		double result =0; 
		
		for (int i = 0; i < list.size(); i++) {
			God e = list.get(i);

			if (find(e.x) != find(e.y)) {
				result += e.weight;
				union(e.x, e.y);
			}
		}
		
		System.out.println(String.format("%.2f", result));

	}
	static double dis(Point2 p1, Point2 p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
	
	static int find(int x) {
		if(parent[x] == x ) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x= find(x);
		y = find(y);
		
		if(x!=y) {
			parent[y] =x;
		}
	}
	
}
class Point2 {
	double x;
	double y;
	int num;

	public Point2(double x, double y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

class God implements Comparable<God>{
	int x;
	int y;
	double weight;
	
	public God(int x, int y, double weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}

	@Override
	public int compareTo(God o) {
		if(this.weight < o.weight) {
			return -1;
		}
		return 1;
	}
}	