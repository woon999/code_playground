package baekjoon.ttone.greedy;

// #1700 그리디 멀티탭 스케줄링 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class MultitabScheduling {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<k; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		
		
		Set<Integer> set = new HashSet<>();
		
		int cnt =0;
		for(int i=0; i<k; i++) {
			int num = list.get(i);
			if(set.contains(num)) continue;
			if(set.size()<n && set.add(num)) continue;
			
			int max = -1, idx =-1;
			for(int s : set) {
				int tmp=0;
				List<Integer> sub = list.subList(i+1, k);
				if(sub.contains(s)) {
					tmp = sub.indexOf(s)+1;
				}
				else {
					tmp = k-i-1;
				}
				
				if(tmp > max) {
					max = tmp;
					idx= s;
				}
			}
			
			set.remove(idx);
			set.add(num);
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
