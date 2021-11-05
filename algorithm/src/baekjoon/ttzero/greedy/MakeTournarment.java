package baekjoon.ttzero.greedy;

// #2262
import java.io.*;
import java.util.*;

public class MakeTournarment {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		
		List<Integer> list = new ArrayList<Integer>();
		int min = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) 
			list.add(Integer.parseInt(st.nextToken()));
			
		int max = n;	 
		for(int i=0; i<n-1; i++) {
			int idx = list.indexOf(max);	
			
			if(idx == 0)
				min += list.get(idx) - list.get(idx + 1);
			else if(idx == list.size()-1) 
				min += list.get(idx) - list.get(idx -1);
			
			else
				min += Math.min(list.get(idx) - list.get(idx-1), list.get(idx) - list.get(idx+1));
			
			list.remove(idx);
			max --;
		}
		
		System.out.println(min);

	}
}
