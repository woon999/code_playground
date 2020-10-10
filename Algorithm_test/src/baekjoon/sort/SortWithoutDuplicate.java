package baekjoon.sort;

// #10867
import java.io.*;
import java.util.*;

public class SortWithoutDuplicate {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		   Set<Integer> set = new TreeSet<Integer>();
		   StringTokenizer st = new StringTokenizer(br.readLine());
		   
		   while(n-->0) {
	            set.add(Integer.parseInt(st.nextToken()));
	        }
	        
	        Iterator it = set.iterator();
	        
	        while(it.hasNext()) {
	            System.out.print(it.next() + " ");
	        }

	}
}
