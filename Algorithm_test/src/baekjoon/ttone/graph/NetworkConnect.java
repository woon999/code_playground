package baekjoon.ttone.graph;

// #1922 graph 네트워크 연결 (최소 신장(spanning)트리- 크루스칼알고리즘) 
import java.io.*;
import java.util.*;

public class NetworkConnect {
	static int[] parents;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		
		List<Net> list = new ArrayList<>();
		parents = new int[n+1];
		for (int i = 1; i <= n; i++) { 
	    	parents[i] = i; 
	    } 
		
		StringTokenizer st = null;
		for(int i=0; i<m; i++) {
			st= new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			list.add(new Net(to, from, value));
		}
		
		Collections.sort(list);
		
		for(Net net : list) {
			System.out.println(net.to +"->" +net.from + " : "+net.value);
		}
		
		int sum = 0; 
		for(int i=0; i<list.size(); i++) {
			Net net = list.get(i);
			
			int rx = find(net.to);
			int ry = find(net.from);
			
			if(!isSameParent(rx, ry)) {
				union(net.to, net.from);
				sum += net.value;
			}
		}
		
		System.out.println(sum);
				
	}
	
	static int find(int x) {
		if(parents[x] == x) return x;
		
		return parents[x] = find(parents[x]);
	}
	
	static void union(int x, int y) {
		int rx = find(x);
		int ry = find(y);
		
		if(rx != ry) {
			parents[ry] = rx;
		}
	}
	
	static boolean isSameParent(int rx, int ry){
		if(rx == ry) return true;
		return false;
	}
}


class Net implements Comparable<Net>{
	int to;
	int from;
	int value;
	
	public Net(int to, int from, int value) {
		this.to = to;
		this.from = from;
		this.value = value;
	}

	@Override
	public int compareTo(Net o) {
		// TODO Auto-generated method stub
		return this.value - o.value;
	}
	
}