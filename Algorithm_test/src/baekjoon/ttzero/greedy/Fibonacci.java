package baekjoon.ttzero.greedy;

// #9009
import java.io.*;
import java.util.*;


public class Fibonacci {

	static ArrayList<Long> fibolist;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		long[] num = new long[n];
		
		long max=0;
		fibolist = new ArrayList<Long>();
		fibolist.add((long)0);
		fibolist.add((long)1);
		
		Stack<Long> res[] = new Stack[n];
		
		for(int i=0; i<n; i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
				
			res[i] = new Stack();
			num[i] = Long.parseLong(st.nextToken());
			
			if(num[i] > max) max =num[i];
					
		}
		
		while(true) {
			long l1 = fibolist.get(fibolist.size()-1);
			long l2 = fibolist.get(fibolist.size()-2);
			fibolist.add(l1 + l2);
//			System.out.println(l1 +" ," + l2);
			if(l1 + l2 > max) break;
		}
		
		for(int i=0; i<n; i++) {
			while(true) {
				long x = fibolist.get(solve(num[i]));
				res[i].add(x);
				
				num[i] = num[i] - x;
				
				if(num[i] ==0) break;
			}
		}
		
		for(Stack<Long> s : res) {
			while(s.size()>0) {
				System.out.print(s.pop() + " ");
			}
			System.out.println();
		}
		
				
	}
	
	static int solve(long num) {
		int idx = 0;
		while(true) {
			if(fibolist.get(idx) > num) return idx-1;
			idx++;
		}
				
	}
}
