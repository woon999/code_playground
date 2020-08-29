package baekjoon.greedy;

// #1202 
import java.io.*;
import java.util.*;

public class JewelThief {

	static int n,k;
	static Jew[] box;
	static int[] bag;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		box = new Jew[n];
		bag = new int[k];
		
		
		
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			box[i] = new Jew(m,v); 
		}
		
		for(int i=0; i<k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(box);
		Arrays.sort(bag);
		
		solve();
		
	}
	
	static void solve() {
		Queue<Integer> q = new PriorityQueue<Integer>();
		long result =0;
		int j=0;
		
		for(int i=0; i<k; i++) {
			while(j<n && box[j].w <= bag[i]) {
				q.add(-box[j].v);
				j++;
			}
			
			if(!q.isEmpty()) {
				result+=Math.abs(q.poll());
			}
		}
		
		System.out.println(result);
	}
}

class Jew implements Comparable<Jew>{
	int w;
	int v;
	
	public Jew(int w, int v) {
		this.w = w;
		this.v = v;
	}

	@Override
	public int compareTo(Jew o) {
		if(this.w <= o.w) {
			return -1;
		}
		else {
			return 1;
		}
	}
}