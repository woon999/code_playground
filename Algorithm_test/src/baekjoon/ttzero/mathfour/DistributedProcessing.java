package baekjoon.ttzero.mathfour;

// #1009
import java.io.*;
import java.util.StringTokenizer;

public class DistributedProcessing {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			int num = 1;
			for(int j=0; j<b; j++) {
				num = (num*a)%10;
			}
			if(num==0) num=10;
			
			System.out.println(num);
		}
	}
}
