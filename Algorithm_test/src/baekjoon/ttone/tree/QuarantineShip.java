package baekjoon.ttone.tree;

// #13209 tree 검역소 - 우선순위 큐 풀이  
import java.io.*;
import java.util.*;

public class QuarantineShip {

	static long cnt, mid;
	static int[] data;
	static List<Integer>[] list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		for(int t=0; t<tc; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			data = new int[n+1];
			long sum = 0;
			for(int i=1; i<n+1; i++) {
				data[i] = Integer.parseInt(st.nextToken());
				sum += data[i];
			}
			
			list = new ArrayList[n+1];
			for(int i=1; i<n+1; i++) {
				list[i] = new ArrayList<>();
			}
			
			for(int i=0; i<n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				list[a].add(b);
				list[b].add(a);
			}
			
			long left = 0;
			long right = sum;
			int max =0;
			for (int i = 1; i <n+1; i++) {
				max = Math.max(max, data[i]);
			}
			while(left +1< right) {
				long m = (left+right)/2;
				mid = m; cnt = 0;
				System.out.println("------start------" + mid);
				System.out.println(left+","  +right);
				solve(1,1);
				System.out.println("## cnt : " + cnt);
				if(max <= m && cnt <= k) {
					right = m;
				}else {
					left= m;
				}
				System.out.println();
			}
			sb.append(right).append("\n");
		}
		System.out.println(sb.toString());
	} 
	
	
	static long solve(int pos, int prev){
		long now = data[pos];
		
		Queue<Long> q = new PriorityQueue<>(Collections.reverseOrder());
		for(int nxt : list[pos]) {
			if(nxt != prev) { 
				long t = solve(nxt, pos);
				System.out.println("pos :" + pos + ", child :"+ nxt + " : ("+ t+"+"+ now+")");
				now += t;
				q.offer(t);
			}
		}
		
		while(!q.isEmpty()&& now > mid) {
			
			long remove = q.poll();
			System.out.println("#바리게이트 " + pos+"~"+prev+" : (" +now+"-"+ remove+ ") / " +now+" > " + mid);
			now -= remove;
			cnt++;
		}
		return now;
	}
}