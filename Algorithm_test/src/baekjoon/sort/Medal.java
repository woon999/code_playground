package baekjoon.sort;

// #8979
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Medal {

	static int n;
	static int k;
	static int[] cnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Box> pq = new PriorityQueue<Box>();
		
		for(int i=0; i<n; i++) {
			st= new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			pq.offer(new Box(index, g, s, b));
		}
		
		int rank =1;
		Box tmp = pq.poll();
		if(tmp.idx ==k) {
			System.out.println(rank);
		}else {
			int same =0;
			
			while(!pq.isEmpty()) {
				Box m = pq.poll();
				if(!(tmp.gold==m.gold && tmp.silver==m.silver &&tmp.bronze==m.bronze)){
					tmp=m;
					rank++;
					if(same>0) {
						rank+=same;
						same=0;
					}
				}else {
					same++;
				}
				if(m.idx==k) {
					System.out.println(rank);
					
				}
				
			}
		}
		
		
	}
}

class Box implements Comparable<Box>{

	int idx;
	int gold;
	int silver;
	int bronze;
	
	public Box(int idx, int gold, int silver, int bronze) {
		this.idx = idx;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
	}
	
	@Override
	public int compareTo(Box o) {
		if(this.gold==o.gold) {
			if(this.silver==o.silver) {
				return -(this.bronze-o.bronze);
			}else return -(this.silver-o.silver);
		}else return -(this.gold-o.gold);
	}
	
}
