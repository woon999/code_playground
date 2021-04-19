package baekjoon.ttzero.greedy;

// #2613
import java.io.*;
import java.util.StringTokenizer;

public class NumberMarble {

	static int n,m;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
	
		arr = new int[n+1];
		
		int right = 0;
		int left = 0;
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<n+1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			right += arr[i];
			left = Math.max(left, arr[i]);
		}
		
		int mid=0;
		
		while(left<= right) {
			mid = (left+ right) >>1;
			int cnt = isPossible(mid);
			
			if(cnt> m) {
				left =mid +1;
			}
			else {
				right = mid -1;
			}
		}
		
		System.out.println(left);
		
		int cnt=0, sum =0, i;
		
		for(i=1; i<n+1; i++) {
			sum += arr[i];
			if( sum > left) {
				m--;
				cnt = cnt==0? 1 : cnt;
				System.out.print(cnt+" ");
				sum = arr[i];
				cnt =0;
			}
			cnt ++;
			
			if(m == n-i+1) break; //각 그룹에 구슬이 적어도 1개씩 있어야함.
			
		}
		
	for(; i<n+1; i++) {
		System.out.print(cnt+" ");
		cnt =1;
	}
	
		
	}
	
	static int isPossible(int mid) {
		int s =0 , cnt =1;
		for(int i=1; i<n+1; i++) {
			s += arr[i];
			if(s>mid) {
				s = arr[i];
				cnt++;
			}
		}
		return cnt;
	}
}
