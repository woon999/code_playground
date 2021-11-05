package baekjoon.ttzero.greedy;

// #1138
import java.io.*;
import java.util.*;

public class OneLine {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] line = new int[n+1];
		List<Integer> list = new ArrayList<>();
		
		StringTokenizer st= new StringTokenizer(br.readLine());		
		for(int i=1; i<n+1; i++) {	
			line[i] = Integer.parseInt(st.nextToken());
			
		}
		
		
		for(int i =n; i>0; i--) {
			list.add(line[i], i);
		}
		
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i)+" ");
		}
	}
}
