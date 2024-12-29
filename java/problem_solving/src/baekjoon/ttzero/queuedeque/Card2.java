package baekjoon.ttzero.queuedeque;

// #2146
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Card2 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new LinkedList<Integer>();
		
		for(int i =1; i<n+1; i++) {
			q.offer(i);
		}
		
		while(q.size()>1) {
			q.poll();
			int a = q.poll();
			q.offer(a);
		}
		
		System.out.println(q.poll());
	}
}
