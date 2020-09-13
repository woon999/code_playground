package baekjoon.greedy;

// #8980
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Postbox {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int m = Integer.parseInt(br.readLine());

		int[] boxs = new int[n+1]; // index에 도착할 때 담긴 박스의 개수 
		ArrayList<City> cities = new ArrayList<City>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			cities.add(new City(from,to,weight));
		}
		
		Collections.sort(cities); //마을 오름차순 정렬 
		
		int count =0;
		for(City cs : cities) {
			int ns = cs.s;
			int ne = cs.e;
			int nw = cs.w;
			
			int max =0;
			
			boolean check = true;
			for(int i = ns; i <ne; i++) {
				if(boxs[i]>= c) {
					check = false;
					break;
				}
				max = Math.max(max, boxs[i]);
			}
			
			
			if(check) {
				int unloads = c - max;
				if(unloads > nw) {
					unloads = nw;
				}
				count += unloads;
				
				for(int i=ns; i<ne; i++) {
					boxs[i] += unloads;
				}
			}
			
					
		}
		
		System.out.println(count);

	}
}

class City implements Comparable<City>{

	int s;
	int e;
	int w;
	
	public City(int s, int e, int w) {
		this.s = s;
		this.e = e;
		this.w = w;
	}
	@Override
	public int compareTo(City o) {
		if(this.e< o.e) return -1;
		
		else if(this.e == o.e) return 0;
		
		else return 1;
	}
	
}

