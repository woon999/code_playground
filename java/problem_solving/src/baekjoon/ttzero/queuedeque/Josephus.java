package baekjoon.ttzero.queuedeque;

// #11866
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Josephus {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	

		Queue<Integer> q = new LinkedList<Integer>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] jose = new int[n];
		int[] arr = new int[k];
		
		int idx = 0;
		
		for(int i =1; i<n+1; i++) {
			q.offer(i);
		}
		
	
		while(q.size()>0) {
			
			for(int i =0; i<k-1;i++) {
				arr[i] = q.poll();
				q.add(arr[i]);
			}
			
			jose[idx++] = q.poll();
			
		}

		System.out.print("<");
		for(int i=0; i<jose.length-1; i++) {
			System.out.print(jose[i] + ", ");
		}
		System.out.print(jose[n-1]+">");
		
	}
}
