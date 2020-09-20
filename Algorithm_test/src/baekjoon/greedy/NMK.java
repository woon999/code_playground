package baekjoon.greedy;

// #1201
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class NMK {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		
		if(m+k-1 <=n && n<= m*k) {
			int[] a = new int[n];
			for(int i=0; i<n; i++) {
				a[i] = i+1;
			}
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(0);
			list.add(k);
			
			n -= k;
			m -= 1;
			
			int size = (m==0)? 1: n/m;
			int r = (m==0)? 0 : n%m;
			
			for(int i=0; i<m; i++) {
				int v = list.get(list.size()-1) +size;
				if(r>0) {
					v++;
					r -=1;
				}
				list.add(v);
			}
			
			for(int i=0; i<list.size()-1; i++) {
				int start = list.get(i);
				int end = list.get(i+1)-1;
				
				while(start<end) {
					int tmp = a[start];
					a[start] = a[end];
					a[end] = tmp;
					
					start += 1;
					end -= 1;
					
				}
						
			}
			
			for(int i=0; i<a.length; i++) {
				System.out.print(a[i]+" ");
			}
			
		}else {
			System.out.println(-1);
		}
		
	}
}
