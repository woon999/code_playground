package baekjoon.ttone.implementation;

// #1959 implementation 달팽이3  
import java.io.*;
import java.util.StringTokenizer;

public class Snail3 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long m = Long.parseLong(st.nextToken());
		long n = Long.parseLong(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		if(m>n) {
			sb.append(((n-1)*2+1)+"\n");
		}else {
			sb.append(((m-1)*2)+"\n");
		}
		
		if(m==n) {
			if(m%2==1) {
				sb.append((m/2+1)+" "+(n/2+1) +"\n");
			}else {
				sb.append((m/2+1)+" "+(n/2) +"\n");
			}
	 	}else if(m>n) {
	 		if(n%2==0) {
	 			sb.append((n/2+1) +" "+(n/2)+"\n");
	 		}else {
	 			sb.append((n/2+1+(m-n)) +" "+(n/2+1)+"\n");
	 		}
	 	}else {
	 		if(m%2==0) {
	 			sb.append((m/2+1) +" "+(m/2)+"\n");
	 		}else {
	 			sb.append((m/2+1)+" "+(m/2+1+(n-m))+"\n");
	 		}
	 	}
		
		System.out.println(sb.toString());
	}
	
}
