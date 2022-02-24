package baekjoon.tttwo.number;

// #2824 number GCD 
import java.io.*;
import java.util.*;

public class GCD_2824 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		
		Map<Integer, Integer> aMap, bMap;
		aMap = new HashMap<>();
		bMap = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(st.nextToken());
			getCommonFactor(num, aMap);
		}
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) {
			int num = Integer.parseInt(st.nextToken());
			getCommonFactor(num, bMap);
		}
		
		boolean flag = false;
		long gcd = 1L;
		for(int key : aMap.keySet()) {
			if(bMap.containsKey(key)) {
				int min = Math.min(aMap.get(key), bMap.get(key));
				for(int i=0; i<min; i++) {
					gcd *= key;
					if(gcd>=1_000_000_000) {
						gcd %= 1_000_000_000;
						flag = true;
					}
				}
			}
		}
		if(flag) {
			System.out.printf("%09d", gcd);
			return;
		}
		
		System.out.println(gcd);
	}
	
	static void getCommonFactor(int num, Map<Integer, Integer> map) {
		for(int i=2; i*i<=num; i++) {
			while(num%i ==0) {
				map.put(i, map.getOrDefault(i, 0)+1);
				num /=i;
			}
		}
		
		if(num != 1) {
			map.put(num, map.getOrDefault(num, 0)+1);
		}
	}
}


