package baekjoon.ttone.tree;

// #3176 tree 도시 네트워크 - LCA + dp 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CityNetwork {

	static class City{
		int to;
		int dis;
		
		public City(int to, int dis) {
			this.to = to;
			this.dis = dis;
		}
	}
	static int n,h;
	static List<City>[] list;
	static int[][] parent, minRoad, maxRoad;
	static int[] depth;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		depth = new int[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		h = getTreeH();
		
		parent = new int[n+1][h];
		minRoad = new int[n+1][h];
		maxRoad = new int[n+1][h];
		
		boolean[] root = new boolean[n+1];
		for(int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			list[a].add(new City(b,c));
			list[b].add(new City(a,c));
			root[b] = true;
		}
		
		int rIdx=0;
		for(int i=1; i<n+1; i++) {
			if(!root[i]) {
				rIdx=i;
				break;
			}
		}
		
		init(rIdx,1,0);
		fillParents();
		
		StringBuilder sb = new StringBuilder();
		int k = Integer.parseInt(br.readLine());
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			int[] res= LCA(d,e);
			sb.append(res[0]+" "+res[1]);
			if(i!=k-1) sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	
	static int getTreeH() {
		return (int)Math.ceil(Math.log(n)/Math.log(2))+1; 
	}
	
	static void fillParents() {
		for(int i=1; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				
				maxRoad[j][i] = Math.max(maxRoad[j][i-1], maxRoad[parent[j][i-1]][i-1]);
				minRoad[j][i] = Math.min(minRoad[j][i-1], minRoad[parent[j][i-1]][i-1]);
			}
		}
	}
	
	static void init(int cur, int h, int pa) {
		depth[cur] = h;
		for(City nxt : list[cur]) {
			if(nxt.to != pa) {
				init(nxt.to, h+1,cur);
				minRoad[nxt.to][0] =nxt.dis;
				maxRoad[nxt.to][0] =nxt.dis;
				parent[nxt.to][0] =cur;
			}
		}
	}
	
	static int[] LCA(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		if(ah<bh){
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		int min = 1_000_001;
		int max = -1;
		for(int i=h-1; i>=0; i--) {
			if(Math.pow(2, i) <= depth[a] - depth[b]) {
				min = Math.min(min, minRoad[a][i]);
				max = Math.max(max, maxRoad[a][i]);
				a = parent[a][i];
			}
		}
		
		if(a==b) return new int[] {min,max};
		
		for(int i=h-1; i>=0; i--) {
			if(parent[a][i] != parent[b][i]) {
				min = Math.min(min, Math.min(minRoad[a][i], minRoad[b][i]));
				max = Math.max(max, Math.max(maxRoad[a][i], maxRoad[b][i]));
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		min = Math.min(min, Math.min(minRoad[a][0], minRoad[b][0]));
		max = Math.max(max, Math.max(maxRoad[a][0], maxRoad[b][0]));
		return new int[] {min,max};
	}
	

	static void print() {
		for(int i=1; i<n+1; i++) {
			System.out.print(depth[i]+" ");
		}
		System.out.println();
		
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(parent[j][i]+" ");
			}
			System.out.println();
		}
		System.out.println("---");
		
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(minRoad[j][i]+" ");
			}
			System.out.println();
		}
		
		System.out.println("---");
		
		for(int i=0; i<h; i++) {
			for(int j=1; j<n+1; j++) {
				System.out.print(maxRoad[j][i]+" ");
			}
			System.out.println();
		}
	}
}
