package baekjoon.tttwo.binarysearch;

// #3079 binarysearch 입국심사 
import java.io.*;
import java.util.*;

public class Immigration {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Long> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			list.add(Long.parseLong(br.readLine()));
		}
		
		Collections.sort(list);
		long left = 0;
		long right = m * list.get(list.size()-1);
		long answer = right;
		while(left <= right) {
			long mid = (left+right)/2;
			
			long p = 0;
			for(long num : list) {
				p += mid/num;
			}
			
			if(p >= m) {
				answer = Math.min(answer, mid);
				right = mid -1;
			}else {
				left = mid +1;
				
			}
		}
		System.out.println(answer);
	}
}
