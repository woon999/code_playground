package baekjoon.ttone.dp;

// #10942 dp,문자열 펠린드롬 - 중간저장 
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Palindrome {

	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		
		int[] num = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			boolean flag = checkPalin(Arrays.copyOfRange(num, start-1, end));
			
			if(flag) {
				sb.append("1\n");
			}else {
				sb.append("0\n");
			}
		}
		
		System.out.println(sb.toString());
	}

	static boolean checkPalin(int[] pattern) {
		int len = pattern.length;
		
		int[] left;
		int[] right;	
		if(len %2 ==1) {
			left = Arrays.copyOfRange(pattern, 0, (len-1)/2);
			right = Arrays.copyOfRange(pattern, (len-1)/2+1, len);
		}else {
			left = Arrays.copyOfRange(pattern, 0, len/2);
			right = Arrays.copyOfRange(pattern, len/2, len);
			
		}
		
//		for(int l : left) {
//			System.out.print(l+" ");
//		}
//		System.out.println();
//		for(int r : right) {
//			System.out.print(r+" ");
//		}
//		System.out.println();
		
		int end = right.length-1;
		for(int i=0; i< right.length; i++) {
			if(left[i] != right[end-i]) {
				return false;
			}
			
		}
		
		return true;
		
		
	}
	

}
