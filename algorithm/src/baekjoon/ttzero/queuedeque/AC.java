package baekjoon.ttzero.queuedeque;

// #5430
import java.io.*;
import java.util.*;

public class AC {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int i=0; i<t; i++) {
			String[] s = br.readLine().split("");
			int n = Integer.parseInt(br.readLine());
			String[] x = br.readLine().replace("[", "").replace("]", "").split(",");
			
			
			String result = ac(s,n,x);
			
			System.out.println(result);
		}
	}
	
	
	static String ac(String[] s, int n, String[] x) {
		Deque<Integer> d = deque(n,x);
		String dir = "L";
		
		for(int i=0; i<s.length;i++) {
			if(s[i].equals("R")) {
				if(dir.equals("L")) {
					dir = "R";
				}else {
					dir = "L";
				}
				
			}
			else if(s[i].equals("D")) {
				if(d.isEmpty()) {
					return "error";
				}
				else {
					if(dir.equals("L")) {
						d.pollFirst();
					}
					else {
						d.pollLast();
					}
				}
			}
		}
		StringBuilder sb = DqPrint(d,dir);
		return sb.toString();
	}
	
	static StringBuilder DqPrint(Deque<Integer> dq, String dir) {
		StringBuilder sb = new StringBuilder("[");
		int len = dq.size();
		for(int i=0; i< len; i++ ) {
			if(dir.equals("L")) {
				sb.append(dq.pollFirst());
			}
			else {
				sb.append(dq.pollLast());
			}
			if(!dq.isEmpty()) sb.append(",");
			
		}
		sb.append("]");
		return sb;
	}
	static Deque<Integer> deque(int n,String[] x){
//		System.out.println("n : "+n +", x.length :" +x.length);
		Deque<Integer> dq = new LinkedList<Integer>();
		for(int i =0; i<n;i++) {
			dq.offer(Integer.parseInt(x[i]));
		}
		return dq;
	}
}
