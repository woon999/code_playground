package baekjoon.ttzero.sort;

// #11728
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ArrayMerge {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		
		while(!pq.isEmpty()) {
			sb.append(pq.poll() +" ");
		}
		System.out.println(sb.toString());
		
	}
}
