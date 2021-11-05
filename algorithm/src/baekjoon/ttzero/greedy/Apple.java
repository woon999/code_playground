package baekjoon.ttzero.greedy;


// #2828
import java.io.*;
import java.util.StringTokenizer;

public class Apple {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int t = Integer.parseInt(br.readLine());
		
		
		int move=0;
		int left=1;
		int right= 1 + (m-1);
		for(int i=0; i<t; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(num > right) {
				move += num - right; // 5-1 =4
				right = num;   // right 5
				left = num - m+1 ; // 5-1 +1 left 5
			}
			else if(num < left) { 
				move += left - num;  //  5-3 =2
				left = num;   // left 3
				right = num + m -1; //3+1 right 3
			}
			
		}
		System.out.println(move);
	}

}
