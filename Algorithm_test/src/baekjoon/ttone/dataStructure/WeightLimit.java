package baekjoon.ttone.dataStructure;

// #1939 dataStructure 중량제한 (bfs,이진탐색) 
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WeightLimit {

	static int n,m, ans;
	static List<City>[] list;
	static boolean[] check;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		for(int i=1; i<n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		int left =0;
		int right =0;
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[a].add(new City(b,w));
			list[b].add(new City(a,w));
			right = Math.max(right, w);
		}
		
		st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		
		while(left<= right) {
			int mid = (left+right)/2;
			ans = -1;
			check = new boolean[n+1];
			dfs(from,to,mid);
			if(ans != -1) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		System.out.println(right);
		
	}
	
	static void dfs(int pos, int target, int limit) {
		
		if(pos == target) {
			ans = pos; 
			return;
		}
		
		check[pos]= true;
		for(City c : list[pos]) {
			if(!check[c.to] && limit <= c.w ) {
				dfs(c.to, target, limit);
			}
		}
	}
	
}
class City{
	int to;
	int w;
	
	public City(int to, int w) {
		this.to = to;
		this.w = w;
	}

}
