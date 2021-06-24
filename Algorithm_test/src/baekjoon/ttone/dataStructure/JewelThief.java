package baekjoon.ttone.dataStructure;

// #1202 dataStructure (1202. 보석 도둑) - 우선순위 큐 
import java.io.*;
import java.util.*;

public class JewelThief {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		List<Jewel> jewInfo = new ArrayList<>();
		int[] bag = new int[k];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewInfo.add(new Jewel(m,v));
		}
		
		for(int i=0; i<k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(bag); // 가방 무게 오름차순 
		Collections.sort(jewInfo); // 보석 무게 기준 오름차순 
		
		Queue<Integer> pq = new PriorityQueue<>((o1,o2) -> (o2-o1));
		long answer = 0;
		int j=0;
		for(int i=0; i<k; i++) {
			while(true) {
				if(j>=n) break;
				Jewel jew = jewInfo.get(j);
				
				if(bag[i] < jewInfo.get(j).weight) break;
				pq.add(jew.price);
				j++;
			}
			
			if(!pq.isEmpty()) {
				answer += Math.abs(pq.poll());
			}
		}
		System.out.println(answer);
	}
}

class Jewel implements Comparable<Jewel>{
	int weight;
	int price;
	
	public Jewel(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	@Override
	public int compareTo(Jewel o) {
		return this.weight - o.weight;
	}
	
}
