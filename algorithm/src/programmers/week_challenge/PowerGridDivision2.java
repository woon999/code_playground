package programmers.week_challenge;

// week9 전력망 둘로 나누기 - union-find
public class PowerGridDivision2 {

	static int[] parents;
	public static void main(String[] args) {
		int n = 9;
		int[][] wires = {
				{1,3}, {2,3}, {3,4}, {4,5},
				{4,6}, {4,7}, {7,8}, {7,9}
		};
		
//		int n = 4;
//		int[][] wires = {
//				{1,2}, {2,3}, {3,4}
//		};
		
//		int n = 7;
//		int[][] wires = {
//				{1,2}, {2,7}, {3,7}, {3,4},
//				{4,5}, {6,7}
//		};
		System.out.println(solution(n, wires));
	}
	
	public static int solution(int n, int[][] wires) {
        int answer = 100;
        int idx =0;
        parents = new int[n+1];
        
        while(idx < n-1) {
        	init(n);
        	
	        for(int i=0; i<wires.length; i++) {
	        	if(idx == i) continue;
	        	int a = wires[i][0];
	        	int b = wires[i][1];
	        	union(a,b);
	        }
	        
	        int tmp =0;
	        for(int i=1; i<n+1; i++) {
	        	if(find(parents[i]) == 1) tmp++;
	        }
	        
	        int res = Math.abs(n-2*tmp);
	        answer = Math.min(res, answer);
	        idx++;
        }
        
        return answer;
    }
	
	static void init(int n) {
		for(int i=1; i<n+1; i++) {
        	parents[i] =i;
        }
	}
	
	static int find(int x) {
		if(parents[x] ==x) return x;
		return find(parents[x]);
	}
	
	static void union(int x, int y) {
		int rx = find(x);
		int ry = find(y);
		if(ry < rx) {
			int tmp = rx;
			rx = ry;
			ry = tmp;
		}
		
		if(rx!=ry) {
			parents[ry]= rx;
		}
	}
}
