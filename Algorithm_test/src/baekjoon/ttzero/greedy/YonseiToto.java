package baekjoon.ttzero.greedy;

// #12018

import java.io.*;
import java.util.*;

public class YonseiToto {
	 public static void main(String[] args) throws Exception {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        int ans = 0;

	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int n = Integer.parseInt(st.nextToken());
	        int m = Integer.parseInt(st.nextToken());
	        int[] min = new int[n]; 

	        for (int i = 0; i < n; i++) {

	            st = new StringTokenizer(br.readLine());
	            int a = Integer.parseInt(st.nextToken()); 
	            int max = Integer.parseInt(st.nextToken());
	            
	            
	            Integer[] point = new Integer[a]; 

	            st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < a; j++) {
	                point[j] = Integer.parseInt(st.nextToken());
	            }

	            Arrays.sort(point, Collections.reverseOrder());

	            if (max > a) {
	                min[i] = 1;
	            } else {
	                min[i] = point[max-1]; 
	            }
	        }

	        Arrays.sort(min);

	        
	        for (int i = 0; i < min.length; i++) {
	            if (m >= min[i]) {
	                m -= min[i];
	                ans++;
	            }
	        }

	        System.out.println(ans);
	    }
}
