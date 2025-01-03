package baekjoon.ttzero.backtracking;

import java.io.*;
import java.util.StringTokenizer;

public class NandM01 {

    static int n,m;
    static int[] num;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();
    

	 public static void main(String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        
	        n = Integer.parseInt(st.nextToken());
	        m = Integer.parseInt(st.nextToken());
	        
	        num = new int[m];
	        check = new boolean[n+1];
	        
	        dfs(0);
	        System.out.println(sb);
	    }
	    static void dfs(int count ) {
	        
	        if(count == m) {
	            sb.append(num[0]);
	            for(int i = 1 ;  i < m; i++) {
	                sb.append(" ");
	                sb.append(num[i]);
	            }
	            sb.append("\n");
	            return;
	        }
	        
	 
	        for(int i = 1 ; i <= n ; i++ ) {
//	        	System.out.println(i+" count:" + count);
//	        	System.out.println(Arrays.toString(num));
//	        	System.out.println(Arrays.toString(check));
//	        	System.out.println("-----");
	            if(!check[i]) {
	            	check[i] = true;
	                num[count] = i;
	                dfs(count+1);
	                check[i] = false;
	            }
	        }
	    }
}
