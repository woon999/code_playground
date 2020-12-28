package baekjoon.mathfour;

// #2455
import java.io.*;
import java.util.StringTokenizer;

public class IntelligenceTrain {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int total = 0;
		int res=0;
		
		for(int i=0; i<4; i++) {
			st =  new StringTokenizer(br.readLine());
			int out = Integer.parseInt(st.nextToken());
			int in = Integer.parseInt(st.nextToken());
			
			total += (in-out);
			res =Math.max(res, total); 
		}
		
		System.out.println(res);
	}
}
