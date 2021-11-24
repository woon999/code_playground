package baekjoon.ttone.string;

// #10266 String 시계 사진들 - KMP 
import java.io.*;
import java.util.*;

public class ClokcPic {

	static final int SIZE = 360_000;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] clock1 = new int[SIZE*2];
		int[] clock2 = new int[SIZE];
		StringTokenizer st = new StringTokenizer(br.readLine());
		 for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			clock1[num]= 1;
			clock1[SIZE+num]= 1;
		 }
		 st = new StringTokenizer(br.readLine());
		 for(int i=0; i<n; i++) {
			 int num = Integer.parseInt(st.nextToken());
			 clock2[num]= 1;
		 }
		 
		 kmp(clock1, clock2);
		
	}
	static void kmp(int[] c1, int[] c2) {
		int[] table = makeTable(c2);
		int idx=0;
		for(int i=0; i<SIZE*2; i++) {
			while(idx > 0 && c1[i] != c2[idx]) {
				idx = table[idx-1];
			}
			if(c1[i] == c2[idx]) {
				if(idx==SIZE-1) {
					System.out.println("possible");
					return;
				}
				else idx++;
			}
		}
		System.out.println("impossible");
		
	}
	static int[] makeTable(int[] clock) {
		int idx=0;
		int[] table = new int[SIZE];
		for(int i=1; i<SIZE; i++) {
			while(idx>0 && clock[i]!=clock[idx]) {
				idx = table[idx-1];
			}
			if(clock[i] == clock[idx]) {
				table[i] = ++idx;
			}
		}
		return table;
	}
}
