package baekjoon.ttone.binarySearch;

// #3020 binarySearch 개똥벌레 - 누적합 or 이진탐색 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bee {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
//		binarySerachSolve(n,h);
		
		prefixSumSolve(n,h);
	}
	// 이진탐색 풀이
	static void binarySerachSolve(int n, int h) throws IOException{
		int[] down = new int[n/2];
		int[] up = new int[n/2];
		for(int i=0; i<n/2; i++) {
			int a = Integer.parseInt(br.readLine());
			int b = Integer.parseInt(br.readLine());
			down[i]=a;
			up[i]=b;
		}
		print(down, up);
		Arrays.sort(up);
		Arrays.sort(down);
		int min = n;
		int cnt=0;
		for(int i=1; i<h+1; i++) {
			int conflict =binarySearch(0, n/2, i, down) + binarySearch(0, n/2, h-i+1, up);

			System.out.println("---" + conflict);
			if(min == conflict) {
				cnt++;
				continue;
			}
			
			if(min > conflict) {
				min = conflict;
				cnt=1;
			}
		}
		
		System.out.println(min +" " +cnt);
	}
	static int binarySearch(int left, int right, int h, int[] arr) {
		while(left<right) {
			int mid = (left+right)/2;
			
			if(arr[mid] >= h) {
				right = mid;
			}else {
				left = mid+1;
			}
		}
		System.out.println(h+"###"  +left+"~" +right);
		return arr.length-right;
	}
	
	// 누적합 풀이
	static void prefixSumSolve(int n, int h) throws IOException{
		int[] down = new int[h+2];
		int[] up = new int[h+2];
		for(int i=1; i<=(n)/2; i++) {
			int a = Integer.parseInt(br.readLine());
			int b = h-Integer.parseInt(br.readLine())+1;
			down[a]++;
			up[b]++;
		}
		for(int i=1; i<=h; i++) {
			down[i] += down[i-1];
		}
		
		for(int i=h; i>=1; i--) {
			up[i] += up[i+1];
		}
		
		print(down, up);
		int min = n;
		int cnt=0;
		for(int i=1; i<h+1; i++	) {
			int dif = (down[h]-down[i-1]) + (up[1]-up[i+1]);
			
			if(dif<min) {
				min = dif;
				cnt=1;
			}else if(dif == min) cnt++;
		}
		System.out.println(min +" " + cnt);
	}
	
	static void print(int[] down, int[] up)	{
		for(int num : down) {
			System.out.print(num+" ");
		}
		System.out.println();
		
		for(int num : up) {
			System.out.print(num+" ");
		}
		System.out.println();
		System.out.println("-----------");
	}
}
