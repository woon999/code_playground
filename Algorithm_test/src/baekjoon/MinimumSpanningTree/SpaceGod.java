package baekjoon.MinimumSpanningTree;

// #1774 
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SpaceGod {

	public static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
	}
	
	
	static int find(int x) {
		if(parent[x] == x ) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static void union(int x, int y) {
		x= find(x);
		y = find(y);
		
		if(x!=y) {
			parent[y] =x;
		}
	}
}

class God implements Comparable<God>{
	int start;
	int end;
	double weight;
	
	public God(int start, int end, double weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(God o) {
		if(this.weight < o.weight) {
			return -1;
		}
		return 1;
	}
}	