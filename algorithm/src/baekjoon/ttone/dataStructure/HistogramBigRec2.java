package baekjoon.ttone.dataStructure;

// #1752 dataStructure 히스토그램 가장 큰 직사각형 - stack 
import java.io.*;
import java.util.Stack;

public class HistogramBigRec2 {

	static int n;
	static int[] arr;	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n =  Integer.parseInt(br.readLine());
		arr = new int[n+2];
		for(int i=1; i<n+1; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Stack<Integer> s = new Stack<>();
		s.push(0);
		int ans =0;
		for(int i=1; i<n+2; i++) {
			while(!s.isEmpty()) {
				int top = s.peek();
				System.out.println(i+" ," +top +" ," + arr[top]+"," + arr[i]);
				if(arr[top] <= arr[i]) break;
				s.pop();
				System.out.println("working : " + arr[top] +" *" +(i-s.peek()-1)+ " = " + arr[top]*(i-s.peek()-1));
				ans = Math.max(ans, arr[top]*(i-s.peek()-1));
			}
			s.push(i);
		}
		
		System.out.println(ans);
		
	}
	
	
	
}
